package mapper;

import bean.Words;

import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public interface WordsMapper {

    List<Words> getWords();

    int deleteByPrimaryKey(Long id);

    int insert(Words record);

    int insertSelective(Words record);

    Words selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Words record);

    int updateByPrimaryKey(Words record);
}