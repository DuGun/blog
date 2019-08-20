package mapper;

import bean.FixedLocation;

public interface FixedLocationMapper {
    int deleteByPrimaryKey(FixedLocation key);

    int insert(FixedLocation record);

    int insertSelective(FixedLocation record);
}