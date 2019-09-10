package serviceImpl.user;

import bean.Words;
import mapper.WordsMapper;
import org.springframework.stereotype.Service;
import service.user.WordsRelated;
import util.SnowFlake;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Service
public class WordsRelatedImpl implements WordsRelated {

    @Resource
    WordsMapper wordsMapper;

    @Resource
    SnowFlake snowFlake;



    @Override
    public List<Words> getWords() {
        List<Words> wordsList=wordsMapper.getWords();
        return wordsList;
    }
}
