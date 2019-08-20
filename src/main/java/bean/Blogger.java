package bean;

public class Blogger {
    private String boggerName;

    private String password;

    private String head;

    private String contact;

    public String getBoggerName() {
        return boggerName;
    }

    public void setBoggerName(String boggerName) {
        this.boggerName = boggerName == null ? null : boggerName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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
}