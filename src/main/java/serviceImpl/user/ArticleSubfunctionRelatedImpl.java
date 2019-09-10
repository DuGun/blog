package serviceImpl.user;

import bean.Article;
import bean.ArticleAndCommentAndSubfunction;
import bean.ArticleSubfunction;
import bean.Comments;
import mapper.ArticleSubfunctionMapper;
import org.springframework.stereotype.Service;
import service.user.ArticleSubfunctionRelated;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Service
public class ArticleSubfunctionRelatedImpl implements ArticleSubfunctionRelated {

    @Resource
    ArticleSubfunctionMapper articleSubfunctionMapper;

    @Override
    public void setArticleAndCommentAndSubfunctionSubfunctionBySubfunction(List<ArticleAndCommentAndSubfunction> list) {
        for(ArticleAndCommentAndSubfunction aaas:list){
            Article article=aaas.getArticle();
            ArticleSubfunction articleSubfunction=articleSubfunctionMapper.selectByPrimaryArticleId(article.getId());
           aaas.setArticleSubfunction(articleSubfunction);
        }
    }
}
