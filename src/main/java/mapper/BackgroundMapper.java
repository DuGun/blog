package mapper;

import bean.Background;

public interface BackgroundMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Background record);

    int insertSelective(Background record);

    Background selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Background record);

    int updateByPrimaryKey(Background record);
}