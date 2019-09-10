package bean;


import java.util.List;

/**
 * 分页对象
 */
public class ArticleAndCommentAndSubfunction {

    private Article article;

    private List<Comments> commentsList;

    private ArticleSubfunction articleSubfunction;

    public ArticleAndCommentAndSubfunction() {
        super();
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public List<Comments> getCommentsList() {
        return commentsList;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.commentsList = commentsList;
    }

    public ArticleSubfunction getArticleSubfunction() {
        return articleSubfunction;
    }

    public void setArticleSubfunction(ArticleSubfunction articleSubfunction) {
        this.articleSubfunction = articleSubfunction;
    }
}
