package service.user;

import bean.Words;

import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public interface WordsRelated {


    /**
     * 获取全部一句话内容
     *
     * @return
     */
    List<Words> getWords();

}
