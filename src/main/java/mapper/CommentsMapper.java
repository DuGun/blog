package mapper;

import bean.Comments;

import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public interface CommentsMapper {
    int deleteByPrimaryKey(long id);

    int insert(Comments record);

    int insertSelective(Comments record);

    Comments selectByPrimaryKey(long id);

    List<Comments> selectByArticleId(long articleId);

    int updateByPrimaryKeySelective(Comments record);

    int updateByPrimaryKey(Comments record);
}