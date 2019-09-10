package service.user;

import bean.BloggerLayout;

import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public interface BloggerLayoutRelated {

    //查询该状态信息

    /**
     *查询状态中指定固定点所存在的内容
     *
     * @param fixed_name
     * @return
     */
    List<BloggerLayout> getBloggerLayoutByPlace(String fixed_name);
}
