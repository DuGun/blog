package mapper;

import bean.BloggerLayout;

public interface BloggerLayoutMapper {
    int deleteByPrimaryKey(BloggerLayout key);

    int insert(BloggerLayout record);

    int insertSelective(BloggerLayout record);

    BloggerLayout selectByPrimaryKey(BloggerLayout key);

    int updateByPrimaryKeySelective(BloggerLayout record);

    int updateByPrimaryKey(BloggerLayout record);
}