package mapper;

import bean.Words;

public interface WordsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Words record);

    int insertSelective(Words record);

    Words selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Words record);

    int updateByPrimaryKey(Words record);
}