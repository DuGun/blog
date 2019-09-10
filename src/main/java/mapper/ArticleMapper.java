package mapper;


import bean.Article;

import java.util.List;
import java.util.Map;

public interface ArticleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Long id,int stauts);

    List<Article> selectByIds(long[] ids);

    List<Article> selectAllExcludeArticle(long[] articleIds,int status);

    List<Article> getAllArticle(int status);

    List<Article> excludeAndLimitQuantities(Map<String,Object> filterConditions);

    List<Article> getArticleLimitQuantities(int limit);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    int count(int status);
}