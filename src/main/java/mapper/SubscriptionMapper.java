package mapper;

import bean.Subscription;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public interface SubscriptionMapper {
    int deleteByPrimaryKey(Subscription key);

    int insert(Subscription record);

    int insertSelective(Subscription record);
}