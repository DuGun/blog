package serviceImpl.user;

import bean.Article;
import bean.ArticleAndCommentAndSubfunction;
import bean.Comments;
import mapper.CommentsMapper;
import org.springframework.stereotype.Service;
import service.user.ArticleCommentsRelated;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Service
public class ArticleCommentsRelatedImpl implements ArticleCommentsRelated {

    @Resource
    CommentsMapper commentsMapper;
    @Override
    public void setArticleAndCommentAndSubfunctionCommentsByComments(List<ArticleAndCommentAndSubfunction> list) {
        for(ArticleAndCommentAndSubfunction aaas:list){
          Article article=aaas.getArticle();
          List<Comments> comments=commentsMapper.selectByArticleId(article.getId());
          if(comments.size()!=0){
              aaas.setCommentsList(comments);
          }
        }
    }
}
