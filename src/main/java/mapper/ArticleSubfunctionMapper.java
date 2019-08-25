package mapper;

import bean.ArticleSubfunction;

public interface ArticleSubfunctionMapper {
    int deleteByPrimaryKey(Long articleId);

    int insert(ArticleSubfunction record);

    int insertSelective(ArticleSubfunction record);

    ArticleSubfunction selectByPrimaryKey(Long articleId);

    int updateByPrimaryKeySelective(ArticleSubfunction record);

    int updateByPrimaryKey(ArticleSubfunction record);
}