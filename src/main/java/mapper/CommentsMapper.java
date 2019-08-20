package mapper;

import bean.Comments;
import bean.CommentsWithBLOBs;

public interface CommentsMapper {
    int deleteByPrimaryKey(String id);

    int insert(CommentsWithBLOBs record);

    int insertSelective(CommentsWithBLOBs record);

    CommentsWithBLOBs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CommentsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CommentsWithBLOBs record);

    int updateByPrimaryKey(Comments record);
}