package serviceImpl.user;

import bean.Classify;
import mapper.ClassifyMapper;
import org.springframework.stereotype.Service;
import service.user.ArticleClassifysRelated;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Service
public class ArticleClassifysRelatedImpl implements ArticleClassifysRelated {

    @Resource
    ClassifyMapper classifyMapper;

    @Override
    public List<Classify> getAllClassIfy() {

        List<Classify> classifyList=classifyMapper.getClassifyAll();

        return classifyList;
    }
}
