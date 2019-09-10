package mapper;

import bean.Classify;

import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public interface ClassifyMapper {
    int deleteByPrimaryKey(long id);

    int insert(Classify record);

    int insertSelective(Classify record);

    Classify selectByPrimaryKey(long id);

    Long[] selectClassifyIds();

    List<Classify> getClassifyAll();

    int updateByPrimaryKeySelective(Classify record);

    int updateByPrimaryKey(Classify record);

}