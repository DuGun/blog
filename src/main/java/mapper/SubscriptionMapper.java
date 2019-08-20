package mapper;

import bean.SubscriptionKey;

public interface SubscriptionMapper {
    int deleteByPrimaryKey(SubscriptionKey key);

    int insert(SubscriptionKey record);

    int insertSelective(SubscriptionKey record);
}