package service.messages;

import bean.Article;
import bean.Background;
import exception.customException.FileTypeException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public interface ArticleOperations {


    /**
     * 将文章对象存储到数据库
     *
     * @param article      文章对象
     * @param contentUrl   访问文章路径
     * @param thumbnailUrl 访问缩略图路径
     */
    void uploadArticleDatabase(Article article, String contentUrl, String thumbnailUrl);

    /**
     * 文章文件以文章内附件上传至服务器
     *
     * @param articleMultipart    文章文件，仅支持一张，必须有
     * @param attachmentMultipart 文章附件，可无
     * @param url                 项目路径，必须有
     * @return
     * @throws IOException
     * @throws FileTypeException 文件格式不在接收格式内
     */
    String uploadArticleAttachmentFile(List<MultipartFile> articleMultipart, List<MultipartFile> attachmentMultipart, String url) throws IOException, FileTypeException;

    /**
     * 文章缩略图上传至服务器
     *
     * @param multipartAttachments 缩略图文件，仅支持一张，可无
     * @param url                  缩略图有的时候，则必须有
     * @return
     * @throws FileTypeException 文件格式不在接收格式内
     * @throws IOException
     */
    String uploadArticlethumbnailFile(List<MultipartFile> multipartAttachments, String url) throws FileTypeException, IOException;

    void fixedBackground(List<Article> articleList, boolean whetherFixed, String fixedName);
}
