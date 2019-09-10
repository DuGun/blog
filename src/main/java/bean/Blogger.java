package bean;

import javax.validation.constraints.NotBlank;

/**
 * 博主专用
 */
public class Blogger {
    private String password;

    private String boggerName;

    private String head;

    private String contact;

    private String introduction;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getBoggerName() {
        return boggerName;
    }

    public void setBoggerName(String boggerName) {
        this.boggerName = boggerName == null ? null : boggerName.trim();
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head == null ? null : head.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }
}