package mapper;

import bean.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(long id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);
}