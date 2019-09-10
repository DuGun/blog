package serviceImpl.user;

import bean.Blogger;
import mapper.BloggerMapper;
import org.springframework.stereotype.Service;
import service.user.BloggerRelated;

import javax.annotation.Resource;

/**
 * @author 脐橙
 * @data 2019-09-06 09:56
 * @Email 847033093@qq.com
 */
@Service
public class BloggerRelatedImpl implements BloggerRelated {

    @Resource
    private BloggerMapper bloggerMapper;


    @Override
    public Blogger getBlogger() {
        Blogger blogger = bloggerMapper.getBlogger();
        return blogger;
    }
}
