package serviceImpl.messages;

import bean.Blogger;
import exception.customException.FileTypeException;
import exception.customException.UserInvalidParameterException;
import mapper.BloggerMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.messages.BloggerOperations;
import util.FileOperationsUtil;
import util.PasswordUtil;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Service
public class BloggerOperationsImpl implements BloggerOperations {


    @Value("${projectFile_article_type}")
    private String article_type;

    @Value("${projectFile_head_type}")
    private String heal_type;

    @Value("${projectFile_blogger_file}")
    private String blogger_file;

    @Resource
    private BloggerMapper bloggerMapper;


    @Override
    public String uploadHead(List<MultipartFile> headMultipartFile, String path) throws FileTypeException, IOException {
        //头像
        if (headMultipartFile == null || headMultipartFile.size() == 0) {
            return null;
        }

        if (headMultipartFile.size() > 1) {
            throw new UserInvalidParameterException("博主头像参数有误");
        }

        if (path == null) {
            throw new NullPointerException();
        }

        if (path.trim().equals("")) {
            throw new IllegalArgumentException("当前path不合法");
        }


        //上传
        StringBuilder blogger_background_file = new StringBuilder();
        //文章文件以及文章附件文件上传服务器的地址
        blogger_background_file.append(path).append(blogger_file);
        String url = null;

        try {
            url = FileOperationsUtil.uploadFiles(headMultipartFile, blogger_background_file.toString(), heal_type, true)[0];
        } catch (FileTypeException e) {
            throw new FileTypeException("头像仅支持如下格式：" + heal_type);
        }

        return blogger_file + url;
    }

    @Override
    public String uploadIntroduction(List<MultipartFile> introductionMultipartFile, List<MultipartFile> introductionAttachmentMultipartFile, String path) throws IOException, FileTypeException {
        if (introductionMultipartFile == null || introductionMultipartFile.size() == 0) {
            return null;
        }

        if (introductionMultipartFile.size() > 1) {
            throw new UserInvalidParameterException("个人简历文件参数有误");
        }

        if (path == null) {
            throw new NullPointerException();
        }

        if (path.trim().equals("")) {
            throw new IllegalArgumentException("当前path不合法");
        }

        StringBuilder blogger_background_file = new StringBuilder();

        //获取当前年数
        LocalDateTime locaDateTime = LocalDateTime.now();

        //获取时间戳毫秒值
        long milliSecond = locaDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();

        //文章文件以及文章附件文件上传服务器的地址
        blogger_background_file.append(path).append(blogger_file).append(milliSecond);

        String url = null;

        //个人简历
        try {
            url = FileOperationsUtil.uploadFiles(introductionMultipartFile, blogger_background_file.toString(), article_type, true)[0];
        } catch (FileTypeException e) {
            throw new FileTypeException("个人简历仅支持如下格式：" + article_type);
        }

        //个人简历附件
        if (introductionAttachmentMultipartFile != null && introductionAttachmentMultipartFile.size() != 0) {
            try {
                FileOperationsUtil.uploadFiles(introductionAttachmentMultipartFile, blogger_background_file.toString(), null, false);
            } catch (IOException e) {
                FileOperationsUtil.deleteFile(path + url, false);
                throw new IOException(e);
            }
        }

        return blogger_background_file.append("/").append(url).delete(0, path.length()).toString();
    }

    @Override
    public void updateBlogger(Blogger blogger, String healUrl, String introductionUrl, String projectUrl) throws IOException, IllegalAccessException {

        Class bloggerClass = blogger.getClass();

        Field[] fields = bloggerClass.getDeclaredFields();

        boolean headNull = true;

        boolean introductionNull = true;

        boolean re = true;

        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(blogger) != null) {
                re = false;
                break;
            }
        }

        if (healUrl != null && !healUrl.trim().equals("")) {
            headNull = false;
        }

        if (introductionUrl != null && !introductionUrl.trim().equals("")) {
            introductionNull = false;
        }

        if (re == true) {
            if (headNull == true && introductionNull == true) {
                throw new UserInvalidParameterException("啥都不更新，你傻逼哟？");
            }
        }

        Blogger existenceBlogger = bloggerMapper.getBlogger();


        if (headNull == false) {
            blogger.setHead(healUrl);

        }

        if (introductionNull == false) {
            blogger.setIntroduction(introductionUrl);
        }

        if (blogger.getPassword() != null && !blogger.getPassword().trim().equals("")) {
            blogger.setPassword(PasswordUtil.getSaltMD5(blogger.getPassword()));
        }

        bloggerMapper.update(blogger);

        if (healUrl != null) {
            if (existenceBlogger.getHead() != null) {
                FileOperationsUtil.deleteFile(projectUrl + existenceBlogger.getHead(), false);
            }
        }
        if (introductionUrl != null) {
            if (existenceBlogger.getIntroduction() != null) {
                File file = new File(projectUrl, existenceBlogger.getIntroduction()).getParentFile();
                FileOperationsUtil.deleteFile(file.getAbsolutePath(), true);
            }

        }
    }


}
