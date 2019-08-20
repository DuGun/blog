package bean;

public class Classify {
    private String id;

    private Integer articleSum;

    private Integer readSum;

    private Integer commentsSum;

    private String classifyName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getArticleSum() {
        return articleSum;
    }

    public void setArticleSum(Integer articleSum) {
        this.articleSum = articleSum;
    }

    public Integer getReadSum() {
        return readSum;
    }

    public void setReadSum(Integer readSum) {
        this.readSum = readSum;
    }

    public Integer getCommentsSum() {
        return commentsSum;
    }

    public void setCommentsSum(Integer commentsSum) {
        this.commentsSum = commentsSum;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName == null ? null : classifyName.trim();
    }
}