package bean;

import javax.validation.constraints.NotNull;

/**
 * 文章附加功能
 */
public class ArticleSubfunction {
    private long articleSubfunctionId;

    @NotNull(message = "{validation_article_subfunction_null_message}")
    private long articleId;

    private int readSum;

    private int commentsSum;

    private int good;

    public long getArticleSubfunctionId() {
        return articleSubfunctionId;
    }

    public void setArticleSubfunctionId(long articleSubfunctionId) {
        this.articleSubfunctionId = articleSubfunctionId;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public int getReadSum() {
        return readSum;
    }

    public void setReadSum(int readSum) {
        this.readSum = readSum;
    }

    public int getCommentsSum() {
        return commentsSum;
    }

    public void setCommentsSum(int commentsSum) {
        this.commentsSum = commentsSum;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }
}