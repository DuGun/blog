package service.user;

import bean.*;

import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public interface ArticleRelated {

    /**
     * 获取置顶文章
     *
     * @param fixed_name 固定地点
     * @param limit 获取多少条数据
     * @param page 页数
     * @return
     */
    List<Article> getTopArticles(String fixed_name,int limit,int page,int status);

    /**
     *根据指定的指定地点获取置顶之外的内容
     *
     * @param fixed_name 固定内容
     * @param limit 获取多少条数据
     * @return
     */
    List<Article> getTopExcludeOfLimitQuantities(String fixed_name,int limit,int status);

    /**
     *处理文章分页
     *
     * @param topArticleList 置顶内容
     * @param fillingArticleList 非置顶内容
     * @param page 页数
     * @param number 页内所容纳的数量
     * @param fixed_name 固定地点
     * @return
     */
    Page<ArticleAndCommentAndSubfunction> getPage(List<Article> topArticleList,List<Article> fillingArticleList,int page, int number,String fixed_name,int status);


}
