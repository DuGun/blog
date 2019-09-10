package test.bean;

import bean.Article;
import bean.User;

/**
 * @author 脐橙
 * @data 2019-09-07 18:12
 * @Email 847033093@qq.com
 */
public class Users {
    private Article article;

    private User user;

    public Users(){
        super();
    }

    public Users(Article article, User user) {
        this.article = article;
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
