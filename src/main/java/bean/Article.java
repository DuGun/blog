package bean;

import java.util.Date;

public class Article {
    private String id;

    private String classifyId;

    private String title;

    private String contentUrl;

    private Date time;

    private Integer readSum;

    private Integer commentsSum;

    private Integer status;

    private String imgUrl;

    private Integer good;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getClassifyId() {
        return classifyId;
    }

    public void setClassifyId(String classifyId) {
        this.classifyId = classifyId == null ? null : classifyId.trim();
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : imgUrl.trim();
    }

    public Integer getGood() {
        return good;
    }

    public void setGood(Integer good) {
        this.good = good;
    }
}