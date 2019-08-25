package service.shared;

import bean.Article;

public interface Shared {

    void uploadShared(Article article, String contentUrl, String thumbnailUrl);
}
