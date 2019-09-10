package service.messages;

import bean.Words;

/**
 * @author 脐橙
 * @data 2019-09-06 09:12
 * @Email 847033093@qq.com
 */
public interface WordsOperations {

    /**
     * 上传一句话
     * @param words 一句话对象
     */
    void uploadWords(Words words);
}
