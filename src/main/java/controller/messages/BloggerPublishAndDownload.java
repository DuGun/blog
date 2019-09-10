package controller.messages;


import bean.Blogger;
import exception.customException.FileTypeException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import service.messages.BloggerOperations;
import util.ResponseBean;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Controller
@RequestMapping("message")
public class BloggerPublishAndDownload {

    @Resource
    ResponseBean responseBean;

    @Resource
    BloggerOperations bloggerOperations;

    /**
     * 用于更新博主信息
     * introductionFile：博主个人简历文件，仅限一件
     * headFile：博主头像文件，仅限一件
     *
     *
     * @param blogger
     * @throws IOException       文件上传异常
     * @throws FileTypeException 文件上传不符合规格异常
     */
    @PostMapping(value = "blogger/update",consumes = {"multipart/form-data"})
    @ResponseBody
    public ResponseBean updateBlogger(Blogger blogger, HttpServletRequest request) throws FileTypeException, IOException, IllegalAccessException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //上传服务器的地址
        String url = request.getSession().getServletContext().getRealPath("").trim();

        List<MultipartFile> headMultipartFile = multipartRequest.getFiles("headFile");
        List<MultipartFile> introductionMultipartFile = multipartRequest.getFiles("introductionFile");
        List<MultipartFile> introductionAttachmentMultipartFile = multipartRequest.getFiles("introductionAttachmentFile");

        //头像
        String healUrl = bloggerOperations.uploadHead(headMultipartFile, url);

        //个人简历
        String introductionUrl = bloggerOperations.uploadIntroduction(introductionMultipartFile,introductionAttachmentMultipartFile, url);

        bloggerOperations.updateBlogger(blogger, healUrl, introductionUrl,url);

        responseBean.setData(null);
        responseBean.setCode(ResponseBean.HTTP_CODE_OK);
        responseBean.setMsg(null);

        //这里如果改了密码还需要清除token

        return responseBean;
    }


}
