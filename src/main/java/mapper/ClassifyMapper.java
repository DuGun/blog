package mapper;

import bean.Classify;

public interface ClassifyMapper {
    int deleteByPrimaryKey(long id);

    int insert(Classify record);

    int insertSelective(Classify record);

    Classify selectByPrimaryKey(long id);

    Long[] selectClassifyIds();

    int updateByPrimaryKeySelective(Classify record);

    int updateByPrimaryKey(Classify record);

}