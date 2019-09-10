package controller.user;

import bean.Blogger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.user.BloggerRelated;
import util.ResponseBean;

import javax.annotation.Resource;

/**
 * @author 脐橙
 * @data 2019-09-06 09:57
 * @Email 847033093@qq.com
 */
@Controller
@RequestMapping("user")
@Validated
public class BloggerRelatedC {

    @Resource
    ResponseBean responseBean;

    @Resource
    BloggerRelated bloggerRelated;


    /**
     * 用于获取博主个人信息
     *
     * @return 博主对象
     */
    @GetMapping("blogger/information")
    @ResponseBody
    public ResponseBean getBloggerInformation() {
        Blogger blogger = bloggerRelated.getBlogger();
        blogger.setPassword(null);

        responseBean.setMsg(null);
        responseBean.setCode(ResponseBean.HTTP_CODE_OK);
        responseBean.setData(blogger);
        return responseBean;
    }

    /**
     * 待开发
     * 用于博主登陆后台
     *
     * @param password 密码
     * @return 返回token以及管理主页所需初始内容
     */
    @GetMapping("login")
    @ResponseBody
    public ResponseBean login(@Validated String password) {

        return null;

    }
}
