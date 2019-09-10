package serviceImpl.messages;

import bean.Classify;
import exception.customException.UserInvalidParameterException;
import mapper.ClassifyMapper;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import service.messages.ClassifyOperations;
import util.SnowFlake;

import javax.annotation.Resource;


/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Service
public class ClassifyOperationsImpl implements ClassifyOperations {

    @Resource
    ClassifyMapper classifyMapper;

    @Resource
    SnowFlake snowFlake;

    @Override
    public void uploadClassify(String classifyName) {

        //生成ID
        long id=snowFlake.nextId();

        Classify classify=new Classify();

        classify.setId(id);

        classify.setClassifyName(classifyName);

        try{
            classifyMapper.insertSelective(classify);
        }catch (DuplicateKeyException e){
                throw new UserInvalidParameterException("文章分类名称重复");
        }


    }
}
