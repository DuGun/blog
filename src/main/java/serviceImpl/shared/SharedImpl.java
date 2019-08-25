package serviceImpl.shared;

import bean.Article;
import exception.customException.BindingException;
import mapper.ArticleMapper;
import mapper.ClassifyMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.shared.Shared;
import util.SnowFlake;

import javax.annotation.Resource;

@Service
public class SharedImpl implements Shared {


    @Resource
    SnowFlake snowFlake;

    @Resource
    ClassifyMapper classifyMapper;

    @Resource
    ArticleMapper articleMapper;


    @Override
    @Transactional
    public void uploadShared(Article article, String contentUrl, String thumbnailUrl) throws BindingException {

        //若有所属的分类，判断该分类是否存在
        long classifyId = article.getClassifyId();

        if (!(classifyId == 0)) {
            boolean thereAre = false;

            //查询分类表，查询该分类是否存在
            Long[] ClassifyNames = classifyMapper.selectClassifyIds();
            for (long l : ClassifyNames) {
                thereAre = l == classifyId;
                if (thereAre == true) {
                    break;
                }
            }
            //不存在则爆异常
            if (thereAre == false) {
                throw new BindingException("绑定分类不存在");
            }
        }

        //生成ID
        long id = snowFlake.nextId();

        //设置id
        article.setId(id);

        //设置正文文件路径
        article.setContentUrl(contentUrl);

        //设置缩略图路径
        //先判断是否有缩略图
        if (!(thumbnailUrl == null)) {
            article.setThumbnailUrl(thumbnailUrl);
        }

        //插入数据库
        articleMapper.insertSelective(article);

    }
}
