package serviceImpl.messages;

import bean.FixedLocation;
import exception.customException.UserInvalidParameterException;
import mapper.FixedLocationMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import service.messages.FixedLocationOperations;
import util.SnowFlake;

import javax.annotation.Resource;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Service
public class FixedLocationOperationsImpl implements FixedLocationOperations {

    @Resource
    FixedLocationMapper fixedLocationMapper;

    @Resource
    SnowFlake snowFlake;

    @Override
    public void uploadFixedLocation(String fixedName) {

        //生成ID
        long id=snowFlake.nextId();

        FixedLocation classify=new FixedLocation();

        classify.setFixedId(id);

        classify.setFixedName(fixedName);

        try{
            fixedLocationMapper.insertSelective(classify);
        }catch (DuplicateKeyException e){
            throw new UserInvalidParameterException("固定地点名称重复");
        }

    }


}
