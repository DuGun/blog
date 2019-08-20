package mapper;

import bean.BloggerLayout;

public interface BloggerLayoutMapper {
    int deleteByPrimaryKey(long id);

    int insert(BloggerLayout record);

    int insertSelective(BloggerLayout record);

    BloggerLayout selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(BloggerLayout record);

    int updateByPrimaryKey(BloggerLayout record);
}