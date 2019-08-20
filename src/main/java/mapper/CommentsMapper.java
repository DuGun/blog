package mapper;

import bean.Comments;

public interface CommentsMapper {
    int deleteByPrimaryKey(long id);

    int insert(Comments record);

    int insertSelective(Comments record);

    Comments selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimary(Comments record);

    int updateByPrimaryKey(Comments record);
}