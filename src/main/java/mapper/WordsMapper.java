package mapper;

import bean.Words;

public interface WordsMapper {
    int deleteByPrimaryKey(long id);

    int insert(Words record);

    int insertSelective(Words record);

    Words selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(Words record);

    int updateByPrimaryKey(Words record);
}