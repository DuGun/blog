package service.user;

import bean.Background;

import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-06 09:48
 * @Email 847033093@qq.com
 */
public interface BackgroundRelated {

    /**
     * 获取背景图
     *
     * @param fixedName 固定地点，可以不传
     * @return 若无固定地点，则返回全部，若有就返回固定地点中的背景图
     */
    List<Background> showBackground(String fixedName);
}
