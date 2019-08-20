package controller.messages;

import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.ResponseBean;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;

@Controller()
public class HomePage {
    @Resource
    ResponseBean responseBean;

    @GetMapping("/message/home")
    @ResponseBody
    public ResponseBean Home(@NotBlank @Length(min = 6) String password){

            responseBean.setMsg("进入管理员首页");

            //判断设备和ip
            //发现同一端中已经存在Token 删除旧Token 存储新的Token
            //颁发token

        return responseBean;
    }

}
