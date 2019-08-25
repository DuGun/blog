package mapper;

import bean.Words;

public interface WordsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Words record);

    int insertSelective(Words record);

    Words selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Words record);

    int updateByPrimaryKey(Words record);
}