package service.messages;

import bean.Blogger;
import exception.customException.FileTypeException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public interface BloggerOperations {
    /**
     * 头像上传
     *
     * @param headMultipartFile
     * @param projectUrl
     * @return
     * @throws IOException
     */
    String uploadHead(List<MultipartFile> headMultipartFile, String projectUrl) throws FileTypeException, IOException;

    /**
     * 个人简历上传
     *
     * @param introductionMultipartFile
     * @param projectUrl
     * @return
     * @throws IOException
     */
    String uploadIntroduction(List<MultipartFile> introductionMultipartFile, List<MultipartFile> introductionAttachmentMultipartFile,String projectUrl) throws FileTypeException, IOException;

    /**
     * 更新博客信息表内容
     *
     * @param blogger
     * @param healUrl
     * @param introductionUrl
     */
    void updateBlogger(Blogger blogger,String healUrl, String introductionUrl,String projectUrl) throws IOException, IllegalAccessException;




}
