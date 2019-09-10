package serviceImpl.user;

import bean.BloggerLayout;
import bean.FixedLocation;
import mapper.BloggerLayoutMapper;
import mapper.FixedLocationMapper;
import org.springframework.stereotype.Service;
import service.user.BloggerLayoutRelated;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Service
public class BloggerLayoutRelatedImpl implements BloggerLayoutRelated {

    @Resource
    FixedLocationMapper fixedLocationMapper;

    @Resource
    BloggerLayoutMapper bloggerLayoutMapper;

    @Override
    public List<BloggerLayout> getBloggerLayoutByPlace(String fixed_name) {

        //先查询该
        FixedLocation fixedLocation=fixedLocationMapper.selectbyName(fixed_name);

        if(fixedLocation==null){
            //抛出异常，固定地点不存在
            System.out.println("固定地点不存在");
            throw new RuntimeException();
        }

        //查询固定地点中状态表内容
        List<BloggerLayout> bloggerLayoutList=bloggerLayoutMapper.selectByFiexdPlace(fixedLocation.getFixedId());

        if(bloggerLayoutList.size()==0){
            return null;
        }
        return bloggerLayoutList;
    }
}
