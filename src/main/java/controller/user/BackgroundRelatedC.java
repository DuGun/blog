package controller.user;

import bean.Background;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.messages.BackgroundOperations;
import service.user.BackgroundRelated;
import util.ResponseBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Controller
@RequestMapping("user")
public class BackgroundRelatedC {

    @Resource
    ResponseBean responseBean;

    @Resource
    BackgroundRelated backgroundRelated;


    /**
     * 用于背景图展示
     *
     * @param fixedName 固定地点，可无
     * @return 背景图List
     */
    @GetMapping("background/show")
    @ResponseBody
    public ResponseBean showBackground(String fixedName){

        List<Background> backgroundList=backgroundRelated.showBackground(fixedName);

        responseBean.setData(backgroundList);
        responseBean.setCode(ResponseBean.HTTP_CODE_OK);
        responseBean.setMsg(null);

        return responseBean;
    }
}
