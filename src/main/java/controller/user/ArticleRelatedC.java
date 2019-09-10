package controller.user;

import bean.*;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.user.ArticleCommentsRelated;
import service.user.ArticleRelated;
import service.user.ArticleSubfunctionRelated;
import util.ResponseBean;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Controller
@RequestMapping("user")
@Validated
public class ArticleRelatedC {

    @Resource
    ArticleRelated articleShowRelated;

    @Resource
    ResponseBean responseBean;

    @Resource
    ArticleCommentsRelated articleComments;

    @Resource
    ArticleSubfunctionRelated articleSubfunctionRelated;

    /**
     * 用于获取文章进行展示
     *
     * @param page       第几页
     * @param number     页所需的量
     * @param fixed_name 固定地点
     * @return
     */
    @GetMapping("articles/show")
    @ResponseBody
    public ResponseBean showArticle(int page, int number, String fixed_name) {

        if (page == 0) {
            page = 1;
        }

        if (number == 0) {
            number = 1000;
        }


        List<Article> topArticleList = articleShowRelated.getTopArticles(fixed_name, number, page,1);


        List<Article> fillingArticleList = null;
        if (topArticleList != null && topArticleList.size() < number) {
            fillingArticleList = articleShowRelated.getTopExcludeOfLimitQuantities(fixed_name, number - topArticleList.size(),1);
        }


        Page<ArticleAndCommentAndSubfunction> pagel = articleShowRelated.getPage(topArticleList, fillingArticleList, page, number, fixed_name,1);


        //设置文章评论内容
        articleComments.setArticleAndCommentAndSubfunctionCommentsByComments(pagel.getList());

        //设置附加对象
        articleSubfunctionRelated.setArticleAndCommentAndSubfunctionSubfunctionBySubfunction(pagel.getList());


        responseBean.setData(pagel);
        responseBean.setCode(ResponseBean.HTTP_CODE_OK);
        responseBean.setMsg(null);

        return responseBean;
    }


}
