package controller.messages;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.messages.FixedLocationOperations;
import util.ResponseBean;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Controller
@RequestMapping("message")
@Validated
public class FixedLocationPublishAndDownload {


    @Resource
    ResponseBean responseBean;

    @Resource
    FixedLocationOperations fixedLocationOperations;
    /**
     * 用于添加固定地点
     *
     * @param fixedName 固定地点名称
     * @return
     */
    @GetMapping("fixed/location/publish")
    @ResponseBody
    public ResponseBean fixedLocationPublish(@NotBlank(message = "固定地点名称不能为空") @Length(max = 39, message = "固定地点名称长度不能大于39")String fixedName){

        fixedLocationOperations.uploadFixedLocation(fixedName);

        responseBean.setData(null);
        responseBean.setMsg(null);
        responseBean.setCode(ResponseBean.HTTP_CODE_OK);

        return responseBean;
    }
}
