package mapper;

import bean.Blogger;

public interface BloggerMapper {
    int insert(Blogger record);

    int insertSelective(Blogger record);
}