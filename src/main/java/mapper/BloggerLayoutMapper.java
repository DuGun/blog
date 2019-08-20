package mapper;

import bean.BloggerLayout;

public interface BloggerLayoutMapper {
    int deleteByPrimaryKey(String id);

    int insert(BloggerLayout record);

    int insertSelective(BloggerLayout record);

    BloggerLayout selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BloggerLayout record);

    int updateByPrimaryKey(BloggerLayout record);
}