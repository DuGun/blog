package serviceImpl.messages;

import exception.ExceptionParameterUtil;
import exception.customException.FileTypeException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.messages.FileOperations;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileOperationsImpl implements FileOperations {

    @Value("${projectFile_article_file}")
    private String article_file;

    @Value("${projectFile_thumbnail_img}")
    private String thumbnail_img;

    @Value("${projectFile_article_type}")
    private String article_type;

    @Value("${projectFile_thumbnail_type}")
    private String thumbnail_type;

    @Resource
    private ExceptionParameterUtil exceptionParameterUtil;

    //日志
    private Logger logger = LoggerFactory.getLogger(FileOperationsImpl.class);

    @Override
    public Map<String, String> releaseArticleFile(MultipartFile articleMultipart, List<MultipartFile> articleContentMultiparts,
                                                  MultipartFile thumbnailMultipart, StringBuilder projectUrl) throws IOException, FileTypeException {

        //获取当前年数
        LocalDateTime locaDateTime = LocalDateTime.now();

        //获取时间戳毫秒值
        Long milliSecond = locaDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();

        HashMap<String, String> map = new HashMap<>();

        //获取名字
        String articleName = articleMultipart.getOriginalFilename();

        //获取后缀
        String articlePrefix = FilenameUtils.getExtension(articleName);//原文件后缀

        //记录原始长度
        int urlLength = projectUrl.length();

        //判断文章文件是否在支持的格式内
        boolean articleSupport = article_type.contains("*") || article_type.contains(articlePrefix) || article_type.contains(articlePrefix.toUpperCase());

        if (!articleSupport) {
            throw new FileTypeException(exceptionParameterUtil.getHtmlTypeErrorMessage());
        }

        //文件File
        String articleUrl = String.valueOf(projectUrl.append(article_file).append(locaDateTime.getYear()).append("/").append(milliSecond));

        File file = new File(articleUrl);

        //判断当前路径是否存在
        if (!file.exists()) {
            //不存在则创建文件
            file.mkdirs();
        }

        try {
            File articleFile = new File(file, articleName);
            map.put("content_url", articleFile.getAbsolutePath());
            articleMultipart.transferTo(articleFile);
        } catch (IOException e) {
            logger.error("----------上传文章文件异常，并且清除已上传的文件----------");
            logger.error("error: {}", e.getMessage(), e);
            FileUtils.forceDelete(file);
            throw new IOException(e);
        }

        //操作附件
        boolean thereAttachments = articleContentMultiparts == null;

        if (!thereAttachments) {

            //上传文章内容附件
            for (MultipartFile multipart : articleContentMultiparts) {
                //获取名字
                String attachmentsName = multipart.getOriginalFilename();

                File articleContentFile = new File(articleUrl, attachmentsName);

                try {
                    multipart.transferTo(articleContentFile);
                } catch (IOException e) {
                    //对于刚才生产的时间戳文件夹进行删除
                    logger.error("----------上传文章附件异常，并且清除已上传的文件----------");
                    logger.error("error: {}", e.getMessage(), e);
                    FileUtils.forceDelete(file);
                    throw new IOException(e);
                }
            }

        }

        //操作缩略图
        boolean haveThumbnail = thumbnailMultipart == null;
        if (!haveThumbnail) {
            //判断缩略图是否在支持的格式内
            //获取名字
            String humbnailName = thumbnailMultipart.getOriginalFilename();

            //获取后缀
            String humbnailNamePrefix = FilenameUtils.getExtension(humbnailName);//原文件后缀

            boolean thumbnailSupport = thumbnail_type.contains("*") || thumbnail_type.contains(humbnailNamePrefix) || thumbnail_type.contains(humbnailNamePrefix.toUpperCase());

            if (!thumbnailSupport) {
                FileUtils.forceDelete(file);
                throw new FileTypeException(exceptionParameterUtil.getImgErrorMessage());
            }

            //还原路径，设置为路径缩略图
            projectUrl.delete(urlLength, projectUrl.length()).append("/").append(thumbnail_img).append("/").append(locaDateTime.getYear());

            File thumbnailFile = new File(String.valueOf(projectUrl));

            //判断当前路径是否存在
            if (!thumbnailFile.exists()) {
                //不存在则创建文件
                thumbnailFile.mkdirs();
            }

            //设置缩略图文件路径
            projectUrl.append("/").append(milliSecond).append(".").append(humbnailNamePrefix);

            File thumbnailFile2 = new File(String.valueOf(projectUrl));
            try {
                thumbnailMultipart.transferTo(thumbnailFile2);
            } catch (IOException e) {
                logger.error("----------上传缩略图异常，并且清除已上传的文件----------");
                logger.error("error: {}", e.getMessage(), e);
                FileUtils.forceDelete(file);
                thumbnailFile2.delete();
                throw new IOException(e);
            }
            map.put("thumbnail_url", thumbnailFile2.getAbsolutePath());
        }

        return map;
    }


}
