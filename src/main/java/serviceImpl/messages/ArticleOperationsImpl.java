package serviceImpl.messages;

import bean.Article;
import bean.BloggerLayout;
import bean.FixedLocation;
import exception.customException.FileTypeException;
import exception.customException.UserInvalidParameterException;
import mapper.ArticleMapper;
import mapper.BloggerLayoutMapper;
import mapper.ClassifyMapper;
import mapper.FixedLocationMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.messages.ArticleOperations;
import util.DatabaseParameter;
import util.FileOperationsUtil;
import util.SnowFlake;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleOperationsImpl implements ArticleOperations {
    @Resource
    SnowFlake snowFlake;

    @Resource
    ClassifyMapper classifyMapper;

    @Resource
    ArticleMapper articleMapper;

    @Value("${projectFile_file_mkdir}")
    private String projectFile_file_mkdir;

    @Value("${projectFile_article_file}")
    private String article_file;

    @Value("${projectFile_thumbnail_img}")
    private String thumbnail_img;

    @Value("${projectFile_article_type}")
    private String article_type;

    @Value("${projectFile_thumbnail_type}")
    private String thumbnail_type;

    @Resource
    FixedLocationMapper fixedLocationMapper;

    @Resource
    BloggerLayoutMapper bloggerLayoutMapper;

    @Value("${data_table_blogger_layout_article}")
    private String data_table_blogger_layout_article;


    @Override
    public void uploadArticleDatabase(Article article, String contentUrl, String thumbnailUrl) throws UserInvalidParameterException {

        //若有所属的分类，判断该分类是否存在
        long classifyId = article.getClassifyId();

        if (!(classifyId == 0)) {
            boolean thereAre = false;

            //查询分类表，查询该分类是否存在
            Long[] ClassifyNames = classifyMapper.selectClassifyIds();
            for (long l : ClassifyNames) {
                thereAre = l == classifyId;
                if (thereAre == true) {
                    break;
                }
            }
            //不存在则爆异常
            if (thereAre == false) {
                throw new UserInvalidParameterException("绑定分类不存在");
            }
        }

        //生成ID
        long id = snowFlake.nextId();

        //设置id
        article.setId(id);

        //设置正文文件路径
        article.setContentUrl(contentUrl);

        //设置缩略图路径
        //先判断是否有缩略图
        if (!(thumbnailUrl == null)) {
            article.setThumbnailUrl(thumbnailUrl);
        }

        //插入数据库
        articleMapper.insertSelective(article);

    }

    @Override
    public String uploadArticleAttachmentFile(List<MultipartFile> articleMultipart, List<MultipartFile> attachmentMultipart, String url) throws IOException, FileTypeException {
        if (articleMultipart == null || articleMultipart.size() == 0 || articleMultipart.size() > 1) {
            throw new UserInvalidParameterException("文章参数有误");
        }

        if (url == null) {
            throw new NullPointerException();
        }

        if (url.trim().equals("")) {
            throw new IllegalArgumentException("当前path不合法");
        }

        //获取当前年数
        LocalDateTime locaDateTime = LocalDateTime.now();

        //获取时间戳毫秒值
        long milliSecond = locaDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();

        //用于文章文件以及文章附件文件
        StringBuilder projectArticleUrl = new StringBuilder();
        //文章文件以及文章附件文件上传服务器的地址
        projectArticleUrl.append(url).append(article_file).append(locaDateTime.getYear()).append("/").append(milliSecond);

        String articleurl = null;
        //操作文章文件
        try {
            articleurl = FileOperationsUtil.uploadFiles(articleMultipart, projectArticleUrl.toString(), article_type, false)[0];

        } catch (FileTypeException e) {
            throw new FileTypeException("文章文件仅支持如下格式:" + article_type);
        }


        //用于附件上传
        try {
            if (attachmentMultipart != null && attachmentMultipart.size() != 0) {
                FileOperationsUtil.uploadFiles(attachmentMultipart, projectArticleUrl.toString(), null, false);
            }
        } catch (IOException e) {
            File deleteFile = new File(projectArticleUrl.toString()).getParentFile();
            FileOperationsUtil.deleteFile(deleteFile.getAbsolutePath(), true);
            e.printStackTrace();
            throw new IOException(e);
        }


        return projectArticleUrl.append("/").append(articleurl).delete(0, url.length()).toString();
    }

    @Override
    public String uploadArticlethumbnailFile(List<MultipartFile> multipartAttachments, String url) throws FileTypeException, IOException {

        if (multipartAttachments != null && multipartAttachments.size() > 1) {
            throw new UserInvalidParameterException("缩略图参数有误");
        }
        if (multipartAttachments == null || multipartAttachments.size() == 0) {
            return null;
        }

        if (url == null) {
            throw new NullPointerException();
        }

        if (url.trim().equals("")) {
            throw new IllegalArgumentException("当前path不合法");
        }

        String articleurl = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url).append(thumbnail_img);
        try {
            articleurl = FileOperationsUtil.uploadFiles(multipartAttachments, stringBuilder.toString(), thumbnail_type, true)[0];
        } catch (FileTypeException e) {
            throw new FileTypeException("缩略图文件仅支持如下格式:" + thumbnail_type);
        }

        stringBuilder.append(articleurl).delete(0, url.length());
        return stringBuilder.toString();
    }

    @Override
    public void fixedBackground(List<Article> articleList, boolean whetherFixed, String fixedName) {
        if (articleList == null || fixedName == null) {
            throw new NullPointerException();
        }

        if (articleList.size() == 0) {
            throw new UserInvalidParameterException("参数有误：文章参数为0");
        }

        if (fixedName.trim().equals("")) {
            throw new UserInvalidParameterException("文章指定固定地点不存在");
        }

        FixedLocation fixedLocation = fixedLocationMapper.selectbyName(fixedName);

        if (fixedLocation == null) {
            throw new UserInvalidParameterException("文章指定固定地点不存在");
        }

        List<BloggerLayout> existingBloggerLayoutList = bloggerLayoutMapper.selectByFiexdPlace(fixedLocation.getFixedId());

        //根据参数查看是否设置固定，如果设置固定则将非固定的删除
        if (whetherFixed == true) {
            if (articleList.size() > 1) {
                throw new UserInvalidParameterException("参数有误：设置固定情况下只许设置单张文章");
            }

            if (existingBloggerLayoutList.size() > 0) {
                BloggerLayout bloggerLayout = existingBloggerLayoutList.get(0);

                if (bloggerLayout.getFiexdRank() == DatabaseParameter.FIEXD_RANK_FIXED && bloggerLayout.getFiexdContentId() == articleList.get(0).getId()) {
                    throw new UserInvalidParameterException("参数有误：当前文章已为固定状态");
                } else {
                    //删除固定或其他状态内容
                    bloggerLayoutMapper.deleteFiexdPlaceAll(fixedLocation.getFixedId());
                }
            }
            //设置固定内容
            BloggerLayout newBloggerLayout = new BloggerLayout();
            newBloggerLayout.setTableName(data_table_blogger_layout_article);
            newBloggerLayout.setFiexdContentId(articleList.get(0).getId());
            newBloggerLayout.setFiexdPlace(fixedLocation.getFixedId());
            newBloggerLayout.setFiexdRank(DatabaseParameter.FIEXD_RANK_FIXED);
            bloggerLayoutMapper.insertSelective(newBloggerLayout);

        } else {

            if (articleList.size() > DatabaseParameter.FIEXD_RANK_LONGST) {
                throw new UserInvalidParameterException("文章设置顺序最多允许" + DatabaseParameter.FIEXD_RANK_LONGST + "张");
            }

            //删除全部内容
            bloggerLayoutMapper.deleteFiexdPlaceAll(fixedLocation.getFixedId());

            ArrayList<BloggerLayout> bloggerLayouts = new ArrayList<>();

            //插入新内容
            for (int i = 0; i < articleList.size(); i++) {
                BloggerLayout bloggerLayout = new BloggerLayout();
                //顺序级别
                int rank = 0;
                rank = DatabaseParameter.queryLevel(i + 1, false);
                bloggerLayout.setFiexdPlace(fixedLocation.getFixedId());
                bloggerLayout.setTableName(data_table_blogger_layout_article);
                bloggerLayout.setFiexdRank(rank);
                bloggerLayout.setFiexdContentId(articleList.get(i).getId());
                bloggerLayouts.add(bloggerLayout);

            }
            bloggerLayoutMapper.insertAll(bloggerLayouts);
        }

    }


}
