package controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import util.ResponseBean;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class Test01 {


    private String article_file = "/blogResources/article/files";

    @PostMapping(value = "/test/aaa", consumes = {"multipart/form-data"})
    @ResponseBody
    public ResponseBean aaaa(HttpServletRequest request) throws Exception {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;


        List<MultipartFile> multipartArticles = multipartRequest.getFiles("articleFile");
        List<MultipartFile> multipartThumbnails = multipartRequest.getFiles("thumbnail");
        List<MultipartFile> multipartAttachments = multipartRequest.getFiles("attachmentFiles");
//
//
//        List<MultipartFile> multipartAttachments = multipartRequest.getFiles("attachmentFiles");
//
//        //获取当前年数
//        LocalDateTime locaDateTime = LocalDateTime.now();
//        int year = locaDateTime.getYear();
//
//        //获取时间戳毫秒值
//        long milliSecond = locaDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
//
//        String type = "*";
//
//        StringBuilder pathBuilder = new StringBuilder();
//        pathBuilder.append(article_file).append("/").append(year).append("/").append(milliSecond);
//
//        String path = request.getSession().getServletContext().getRealPath(String.valueOf(pathBuilder));
//
//        try {
//            String[] ss=fileOperations.uploadFiles(multipartAttachments, path, type, true);
//            System.out.println(67890);
//        } catch (IOException e) {
//            System.out.println("上传文件途中出现异常");
//        }

        return null;
    }
}
