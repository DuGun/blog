package bean;

public class ArticleSubfunction {
    private long articleId;

    private int readSum;

    private int commentsSum;

    private int good;

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