package bean;

import java.util.Date;

public class Article {
    private long id;

    private long classifyId;

    private String title;

    private String contentUrl;

    private Date time;

    private int readSum;

    private int commentsSum;

    private int status;

    private String imgUrl;

    private int good;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(long classifyId) {
        this.classifyId = classifyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl == null ? null : contentUrl.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }
}