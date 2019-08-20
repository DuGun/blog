package mapper;

import bean.Background;

public interface BackgroundMapper {
    int deleteByPrimaryKey(long id);

    int insert(Background record);

    int insertSelective(Background record);

    Background selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(Background record);

    int updateByPrimaryKey(Background record);
}