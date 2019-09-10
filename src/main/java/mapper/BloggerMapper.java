package mapper;

import bean.Blogger;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public interface BloggerMapper {
    int insert(Blogger record);

    int insertSelective(Blogger record);

    Blogger getBlogger();

    void update(Blogger blogger);
}