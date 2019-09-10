package bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 订阅对象
 */
public class Subscription {
    @NotBlank(message = "{validation_email_rules_message}")
    //后续加上邮箱正则表达式
    private String email;

    @NotNull(message = "validation_classify_parameter_message")
    private long classifyId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(long classifyId) {
        this.classifyId = classifyId;
    }
}