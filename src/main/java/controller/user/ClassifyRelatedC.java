package controller.user;

import bean.Classify;
import mapper.ClassifyMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.user.ArticleClassifysRelated;
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
public class ClassifyRelatedC {

    @Resource
    ResponseBean responseBean;

    @Resource
    ArticleClassifysRelated articleClassifysRelated;

    /**
     * 用于获取所存在的文章分类
     *
     * @return 文章分类对象
     */
    @GetMapping("article/classify/show")
    @ResponseBody
    public ResponseBean showAeticleClassIfy() {



        List<Classify> classifyList =  articleClassifysRelated.getAllClassIfy();

        responseBean.setMsg(null);
        responseBean.setCode(ResponseBean.HTTP_CODE_OK);
        responseBean.setData(classifyList);


        return responseBean;

    }
}
