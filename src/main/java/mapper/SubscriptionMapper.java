package mapper;

import bean.Subscription;

public interface SubscriptionMapper {
    int deleteByPrimaryKey(Subscription key);

    int insert(Subscription record);

    int insertSelective(Subscription record);
}