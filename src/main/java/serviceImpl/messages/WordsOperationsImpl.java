package serviceImpl.messages;

import bean.Words;
import mapper.WordsMapper;
import org.springframework.stereotype.Service;
import service.messages.WordsOperations;
import util.SnowFlake;

import javax.annotation.Resource;

/**
 * @author 脐橙
 * @data 2019-09-06 09:12
 * @Email 847033093@qq.com
 */
@Service
public class WordsOperationsImpl implements WordsOperations {

    @Resource
    WordsMapper wordsMapper;

    @Resource
    SnowFlake snowFlake;

    @Override
    public void uploadWords(Words words) {
        long id=snowFlake.nextId();
        words.setId(id);
        wordsMapper.insertSelective(words);
    }
}
