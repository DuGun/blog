package mapper;

import bean.FixedLocation;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public interface FixedLocationMapper {

    FixedLocation selectbyName(String fixedName);


    int deleteByPrimaryKey(FixedLocation key);

    int insert(FixedLocation record);

    int insertSelective(FixedLocation record);


}