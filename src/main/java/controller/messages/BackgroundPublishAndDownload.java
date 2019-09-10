package controller.messages;

import bean.Background;
import bean.Blogger;
import com.google.gson.Gson;
import exception.customException.FileTypeException;
import org.apache.commons.io.FileUtils;
import org.hibernate.validator.constraints.Length;
import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import service.messages.BackgroundOperations;
import util.ResponseBean;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Controller
@RequestMapping("message")
@Validated
public class BackgroundPublishAndDownload {

    @Resource
    BackgroundOperations backgroundOperations;

    @Resource
    ResponseBean responseBean;


    /**
     * 用于背景图上传
     * <p>
     * backgroundFiles:背景图文件，必须有，无上限
     *
     * @throws IOException       文件上传异常
     * @throws FileTypeException 文件不符合格式异常
     */
    @PostMapping(value = "background/publish", consumes = {"multipart/form-data"})
    @ResponseBody
    public ResponseBean insertBackground(HttpServletRequest request) throws IOException, FileTypeException {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        //上传服务器的地址
        StringBuilder stringBuilder = new StringBuilder(request.getSession().getServletContext().getRealPath("").trim());

        //获取背景图Multipart
        List<MultipartFile> multipartBackground = multipartRequest.getFiles("backgroundFiles");

        String[] urls = backgroundOperations.uploadArticleFile(multipartBackground, stringBuilder.toString());

        //插入表,返回未上传数
        try {
            backgroundOperations.uploadBackgroundDatabase(urls);
        } catch (DuplicateKeyException e) {
            for (String path : urls) {
                File file = new File(stringBuilder.toString(), path);
                file.delete();
            }
            throw new DuplicateKeyException(e.getMessage());
        } catch (RuntimeException e) {
            for (String path : urls) {
                File file = new File(stringBuilder.toString(), path);
                file.delete();
            }
            throw new RuntimeException(e);
        }

        responseBean.setData(null);
        responseBean.setCode(ResponseBean.HTTP_CODE_OK);
        responseBean.setMsg(null);

        return responseBean;
    }

    /**
     * 用于设置背景图固定或者置顶顺序
     *
     * @param backgroundList 背景图片List,当设置固定只能传一个过来，否之则只允许最大限度(目前为8张)
     * @param whetherFixed   是否设置固定
     * @param fixedName      固定地点名称
     * @return
     */
    @PostMapping(value = "background/fixed",consumes = {"application/json"})
    @ResponseBody
    public ResponseBean updateBackground(@RequestBody @Validated List<Background> backgroundList, boolean whetherFixed, @NotBlank(message = "固定地点不能为空") @Length(max = 39, message = "固定地点参数不合法") String fixedName) {


        backgroundOperations.fixedBackground(backgroundList, whetherFixed, fixedName);

        responseBean.setData(null);
        responseBean.setCode(ResponseBean.HTTP_CODE_OK);
        responseBean.setMsg(null);
        return responseBean;
    }




}
