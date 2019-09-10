package bean;

import javax.validation.constraints.NotBlank;

/**
 * 用户表
 */
public class User {
//    @NotBlank(message = "{validation_email_rules_message}")
    //后续加上正则表达式
    private String email;

    private String head;

    private String userName;

    private int goodSun;

    private int commentsSum;

    private String address;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public int getGoodSun() {
        return goodSun;
    }

    public void setGoodSun(int goodSun) {
        this.goodSun = goodSun;
    }

    public int getCommentsSum() {
        return commentsSum;
    }

    public void setCommentsSum(int commentsSum) {
        this.commentsSum = commentsSum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }
}