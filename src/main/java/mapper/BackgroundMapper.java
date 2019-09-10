package mapper;

import bean.Background;

import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public interface BackgroundMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Background record);

    int insertSelective(Background record);

    int insertSelectiveAll(List<Background> backgroundList);

    Background selectByPrimaryKey(Long id);

    List<Background> selectAll();

    List<Background> selectByPrimaryKeys(long[] keys);

    int updateByPrimaryKeySelective(Background record);

    int updateByPrimaryKey(Background record);

    int sum();
}