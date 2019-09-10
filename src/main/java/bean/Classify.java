package bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 文章分类
 */
public class Classify {

    @NotNull(message = "{validation_classify_parameter_message}")
    private long id;

    private int articleSum;

    private int readSun;

    private int commentsSun;

    @NotBlank(message = "{validation_classify_parameter_message}")
    private String classifyName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getArticleSum() {
        return articleSum;
    }

    public void setArticleSum(int articleSum) {
        this.articleSum = articleSum;
    }

    public int getReadSun() {
        return readSun;
    }

    public void setReadSun(int readSun) {
        this.readSun = readSun;
    }

    public int getCommentsSun() {
        return commentsSun;
    }

    public void setCommentsSun(int commentsSun) {
        this.commentsSun = commentsSun;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName == null ? null : classifyName.trim();
    }
}