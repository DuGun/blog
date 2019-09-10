package service.user;

import bean.Classify;

import java.util.List;

public interface ArticleClassifysRelated {

    /**
     * 获取文章分类名称
     *
     * @return 全部文章分类List
     */
    List<Classify> getAllClassIfy();
}
