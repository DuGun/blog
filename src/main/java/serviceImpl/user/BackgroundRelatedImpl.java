package serviceImpl.user;

import bean.Background;
import bean.BloggerLayout;
import bean.FixedLocation;
import exception.customException.UserInvalidParameterException;
import mapper.BackgroundMapper;
import mapper.BloggerLayoutMapper;
import mapper.FixedLocationMapper;
import org.springframework.stereotype.Service;
import service.user.BackgroundRelated;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-06 09:49
 * @Email 847033093@qq.com
 */
@Service
public class BackgroundRelatedImpl implements BackgroundRelated {

    @Resource
    private FixedLocationMapper fixedLocationMapper;

    @Resource
    private BackgroundMapper backgroundMapper;

    @Resource
    private BloggerLayoutMapper bloggerLayoutMapper;

    //展示 固定地点可有可无
    @Override
    public List<Background> showBackground(String fixedName) {
        List<Background> backgroundList = null;

        if (fixedName == null || fixedName.trim().equals("")) {
            backgroundList = backgroundMapper.selectAll();
        } else {
            FixedLocation fixedLocation = fixedLocationMapper.selectbyName(fixedName);
            if (fixedLocation == null) {
                throw new UserInvalidParameterException("背景图指定固定地点不存在");
            }
            List<BloggerLayout> existingBloggerLayoutList = bloggerLayoutMapper.selectByFiexdPlace(fixedLocation.getFixedId());
            long[] keys = new long[existingBloggerLayoutList.size()];
            for (int i = 0; i < existingBloggerLayoutList.size(); i++) {
                keys[i] = existingBloggerLayoutList.get(i).getFiexdContentId();
            }
            backgroundList = backgroundMapper.selectByPrimaryKeys(keys);

        }

        return backgroundList;
    }
}
