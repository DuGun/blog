package mapper;

import bean.Background;

public interface BackgroundMapper {
    int deleteByPrimaryKey(String id);

    int insert(Background record);

    int insertSelective(Background record);

    Background selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Background record);

    int updateByPrimaryKey(Background record);
}