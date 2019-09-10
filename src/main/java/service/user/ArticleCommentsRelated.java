package service.user;

import bean.ArticleAndCommentAndSubfunction;

import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public interface ArticleCommentsRelated {

    /**
     * 设置文章评论
     *
     * @param list
     */
    void setArticleAndCommentAndSubfunctionCommentsByComments(List<ArticleAndCommentAndSubfunction> list);
}
