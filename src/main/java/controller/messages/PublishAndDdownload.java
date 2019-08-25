package controller.messages;

import bean.Article;
import exception.customException.BindingException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import service.messages.FileOperations;
import service.shared.Shared;
import util.ResponseBean;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("message")
public class PublishAndDdownload {

    @Resource
    FileOperations handleFile;

    @Resource
    Shared shared;

    private static final Logger logger = LoggerFactory.getLogger(PublishAndDdownload.class);
    @Resource
    ResponseBean responseBean;

    @PostMapping(value = "article/upload", consumes = {"multipart/form-data"})
    @ResponseBody
    public ResponseBean show(HttpServletRequest request, @Validated Article article) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        MultipartFile multipartArticle = null;
        MultipartFile multipartThumbnail = null;
        //获取文章文件Multipart
        List<MultipartFile> multipartArticles = multipartRequest.getFiles("articleFile");
        if (multipartArticles.size() == 0 || multipartArticles.size() > 1) {
            //前台传输的参数不对
            responseBean.setMsg(ResponseBean.MSG_PARAMETER_ERROR_MESSAGE);
            responseBean.setCode(ResponseBean.HTTP_CODE_BAD_REQUEST);
            return responseBean;
        } else {
            multipartArticle = multipartArticles.get(0);
        }

        //获取缩略图Multipart
        List<MultipartFile> multipartThumbnails = multipartRequest.getFiles("thumbnail");
        if (multipartThumbnails.size() > 1) {
            //前台传输的参数不对
            responseBean.setMsg(ResponseBean.MSG_PARAMETER_ERROR_MESSAGE);
            responseBean.setCode(ResponseBean.HTTP_CODE_BAD_REQUEST);
            return responseBean;
        } else if (multipartThumbnails.size() > 0) {
            multipartThumbnail = multipartThumbnails.get(0);
        }

        //获取附件List<Multipart>
        List<MultipartFile> multipartAttachments = multipartRequest.getFiles("attachmentFiles");
        if (multipartAttachments.size() == 0) {
            multipartAttachments = null;
        }

        Map<String, String> map = handleFile.releaseArticleFile(multipartArticle, multipartAttachments, multipartThumbnail, new StringBuilder(request.getSession().getServletContext().getRealPath(" ").trim()));


        try {
            shared.uploadShared(article, map.get("content_url"), map.get("thumbnail_url"));
        } catch (BindingException e) {
            //清除已经上传的文件
            Set<String> urlSet = map.keySet();
            for (String url : urlSet) {
                String path = map.get(url);
                File file = new File(path);
                file = file.getParentFile();
                FileUtils.forceDelete(file);
            }
            throw new BindingException(e);

        } catch (Exception e) {
            //清除已经上传的文件
            Set<String> urlSet = map.keySet();
            for (String url : urlSet) {
                String path = map.get(url);
                File file = new File(path);
                file = file.getParentFile();
                FileUtils.forceDelete(file);
            }
            logger.error("error: {}", e.getMessage(), e);
            e.printStackTrace();
            throw new Exception(e);
        }
responseBean.setCode(ResponseBean.HTTP_CODE_OK);
        return responseBean;
    }


}
