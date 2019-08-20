package mapper;

import bean.FixedLocationKey;

public interface FixedLocationMapper {
    int deleteByPrimaryKey(FixedLocationKey key);

    int insert(FixedLocationKey record);

    int insertSelective(FixedLocationKey record);
}