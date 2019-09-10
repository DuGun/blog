package controller.messages;

import bean.Article;
import bean.Background;
import bean.User;
import com.google.gson.Gson;
import exception.customException.FileTypeException;
import exception.customException.UserInvalidParameterException;
import org.hibernate.validator.constraints.Length;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import service.messages.ArticleOperations;
import test.bean.Users;
import util.FileOperationsUtil;
import util.ResponseBean;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import javax.xml.ws.RequestWrapper;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Controller
@RequestMapping("message")
public class ArticlePublishAndDownload {

    @Resource
    ArticleOperations articleOperations;

    private static final Logger logger = LoggerFactory.getLogger(ArticlePublishAndDownload.class);

    @Resource
    ResponseBean responseBean;

    /**
     * 文章上传
     * <p>
     * articleFile:文章文件，只允许一个，必须
     * thumbnailFile:缩略图，只允许一个，非必须
     * attachmentFiles:文章附件，不限制数量，非必须
     *
     * @param article 文章对象
     * @return
     * @throws Exception
     */
    @PostMapping(value = "article/publish", consumes = {"multipart/form-data"})
    @ResponseBody
    public ResponseBean articleDownload(HttpServletRequest request, @Validated Article article) throws FileTypeException, IOException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //上传服务器的地址
        StringBuilder stringBuilder = new StringBuilder(request.getSession().getServletContext().getRealPath("").trim());

        //获取Multipart
        List<MultipartFile> multipartArticles = multipartRequest.getFiles("articleFile");
        List<MultipartFile> multipartAttachments = multipartRequest.getFiles("attachmentFiles");
        List<MultipartFile> multipartThumbnails = multipartRequest.getFiles("thumbnailFile");


        //上传文章文件及文章附件
        String articlePath = articleOperations.uploadArticleAttachmentFile(multipartArticles, multipartAttachments, stringBuilder.toString());

        //上传缩略图

        String articleAttachmentPath = null;
        try {
            articleAttachmentPath = articleOperations.uploadArticlethumbnailFile(multipartThumbnails, stringBuilder.toString());
        } catch (FileTypeException e) {
            File file = new File(stringBuilder.toString(), articlePath).getParentFile();
            FileOperationsUtil.deleteFile(file.getAbsolutePath(), true);
            throw new FileTypeException(e.getTypemessage());
        } catch (IOException e) {
            File file = new File(stringBuilder.toString(), articlePath).getParentFile();
            FileOperationsUtil.deleteFile(file.getAbsolutePath(), true);
            throw new IOException(e);
        }

        try {
            articleOperations.uploadArticleDatabase(article, articlePath, articleAttachmentPath);


        } catch (UserInvalidParameterException e) {
            //清除已经上传的文件
            File artivleFile = new File(String.valueOf(stringBuilder) + articlePath).getParentFile();

            String attachmentFile = String.valueOf(stringBuilder) + articleAttachmentPath;

            FileOperationsUtil.deleteFile(artivleFile.getAbsolutePath(), true);

            FileOperationsUtil.deleteFile(attachmentFile, false);

            throw new UserInvalidParameterException();

        } catch (DuplicateKeyException e) {
            //清除已经上传的文件
            File artivleFile = new File(String.valueOf(stringBuilder) + articlePath).getParentFile();

            String attachmentFile = String.valueOf(stringBuilder) + articleAttachmentPath;

            FileOperationsUtil.deleteFile(artivleFile.getAbsolutePath(), true);

            FileOperationsUtil.deleteFile(attachmentFile, false);

            throw new DuplicateKeyException(e.getMessage());
        } catch (RuntimeException e) {
            //清除已经上传的文件
            File artivleFile = new File(String.valueOf(stringBuilder) + articlePath).getParentFile();

            String attachmentFile = String.valueOf(stringBuilder) + articleAttachmentPath;

            FileOperationsUtil.deleteFile(artivleFile.getAbsolutePath(), true);

            FileOperationsUtil.deleteFile(attachmentFile, false);

            throw new RuntimeException(e);
        }
        responseBean.setCode(ResponseBean.HTTP_CODE_OK);
        responseBean.setMsg(null);
        responseBean.setData(null);
        return responseBean;
    }

    /**
     * 用于设置文章固定或者置顶顺序
     *
     * @param articleList 文章List,当设置固定只能传一个过来，否之则只允许最大限度(目前为8张)
     * @param whetherFixed   是否设置固定
     * @param fixedName      固定地点名称
     * @return
     */
    @PostMapping(value = "article/fixed",consumes = {"application/json"})
    @ResponseBody
    public ResponseBean updateBackground(@RequestBody @Validated List<Article> articleList, boolean whetherFixed, @NotBlank(message = "固定地点不能为空") @Length(max = 39, message = "固定地点参数不合法") String fixedName) {

        articleOperations.fixedBackground(articleList, whetherFixed, fixedName);

        responseBean.setData(null);
        responseBean.setCode(ResponseBean.HTTP_CODE_OK);
        responseBean.setMsg(null);

        return responseBean;
    }


}
