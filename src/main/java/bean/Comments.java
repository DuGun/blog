package bean;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 评论对象
 */
public class Comments {
    private long id;

    @NotNull(message = "{validation_comments_parameter_message}")
    private long articleId;

    private Date time;

    @NotBlank(message = "{validation_comments_parameter_message}")
    @Length(max = 39)
    private String userName;

    @NotBlank(message = "{validation_email_rules_message}")
    //后续加上一个邮箱的正则表达式
    private String email;

    @NotBlank(message = "{validation_comments_parameter_message}")
    private String content;

    private String reply;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply == null ? null : reply.trim();
    }
}