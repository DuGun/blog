package mapper;

import bean.Blggger;

public interface BlgggerMapper {
    int insert(Blggger record);

    int insertSelective(Blggger record);
}