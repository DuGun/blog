package controller.messages;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.messages.ClassifyOperations;
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
public class ClassifysPublishAndDownload {

    @Resource
    ClassifyOperations classifyOperations;

    @Resource
    ResponseBean responseBean;

    /**
     * 用于添加文章分类
     *
     * @param classifyName 分类名称
     * @return
     */
    @GetMapping("classify/publish")
    @ResponseBody
    public ResponseBean classifyDownload(@NotBlank(message = "分类名称不能为空") @Length(max = 39, message = "分类名称长度不能大于39") String classifyName) {

        classifyOperations.uploadClassify(classifyName);

        responseBean.setAll(ResponseBean.HTTP_CODE_OK, null, null);

        return responseBean;
    }
}
