package mapper;

import bean.ArticleSubfunction;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public interface ArticleSubfunctionMapper {
    int deleteByPrimaryKey(long articleSubfunctionId);

    int insert(ArticleSubfunction record);

    int insertSelective(ArticleSubfunction record);

    ArticleSubfunction selectByPrimaryKey(long articleSubfunctionId);

    ArticleSubfunction selectByPrimaryArticleId(long articleId);

    int updateByPrimaryKeySelective(ArticleSubfunction record);

    int updateByPrimaryKey(ArticleSubfunction record);
}