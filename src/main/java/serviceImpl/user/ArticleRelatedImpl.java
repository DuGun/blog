package serviceImpl.user;

import bean.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import exception.customException.SystemInvalidParameterException;
import exception.customException.UserInvalidParameterException;
import mapper.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import service.user.ArticleRelated;
import sun.awt.SunHints;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Service
public class ArticleRelatedImpl implements ArticleRelated {

    @Resource
    ArticleMapper articleMapper;

    @Resource
    FixedLocationMapper fixedLocationMapper;

    @Resource
    BloggerLayoutMapper bloggerLayoutMapper;

    @Resource
    Page<ArticleAndCommentAndSubfunction> articlePage;

    @Value("{fixed_location_arctile_default}")
    String fixed_location_arctile_default;


    //记得做缓存
    @Override
    public List<Article> getTopArticles(String fixed_name, int limit, int page,int status) {

        //获取置顶内容
        //先查询该
        FixedLocation fixedLocation = fixedLocationMapper.selectbyName(fixed_name);

        //获取置顶的文章List
        List<Article> topArticleList = new ArrayList<>();

        if (fixedLocation == null) {
            throw new UserInvalidParameterException("固定地点不存在");
        }

        //查询固定地点中状态表内容
        List<BloggerLayout> bloggerLayoutList = bloggerLayoutMapper.selectByFiexdPlace(fixedLocation.getFixedId());

        if (bloggerLayoutList.size() == 0) {
            return null;
        } else if ((bloggerLayoutList.size() == limit && page == 1) || (bloggerLayoutList.size() < limit && page == 1)) {
            //获取全部置顶内容
            for (BloggerLayout bloggerLayout : bloggerLayoutList) {
                Article article = articleMapper.selectByPrimaryKey(bloggerLayout.getFiexdContentId(),status);
                if (article != null) {
                    topArticleList.add(article);
                }
            }
            return topArticleList;
        } else if (bloggerLayoutList.size() - (page - 1) * limit > 0) {

            if (bloggerLayoutList.size() > limit) {
                int startIndex = 0;
                if (page != 1) {
                    startIndex = (page - 1) * limit;
                }

                if (startIndex < 0) {
                    startIndex = 0;
                }
                int number = limit;
                if ((limit * page) > bloggerLayoutList.size()) {
                    if (bloggerLayoutList.size() % (limit * page) != 0) {
                        number = bloggerLayoutList.size() % limit;
                    }
                }
                for (int i = startIndex; i < startIndex + number; i++) {
                    Article article = articleMapper.selectByPrimaryKey(bloggerLayoutList.get(i).getFiexdContentId(),status);
                    if (article != null) {
                        topArticleList.add(article);
                    }
                }
            }
            return topArticleList;
        }


        return null;
    }

    //记得做缓存
    @Override
    public List<Article> getTopExcludeOfLimitQuantities(String fixed_name, int limit,int status) {

        //获取置顶内容
        //先查询该
        FixedLocation fixedLocation = fixedLocationMapper.selectbyName(fixed_name);

        if (fixedLocation == null) {
            throw new UserInvalidParameterException("固定地点不存在");
        }

        //查询固定地点中状态表内容
        List<BloggerLayout> bloggerLayoutList = bloggerLayoutMapper.selectByFiexdPlace(fixedLocation.getFixedId());
        long[] ids = new long[bloggerLayoutList.size()];
        for (int i = 0; i < bloggerLayoutList.size(); i++) {
            ids[i] = bloggerLayoutList.get(i).getFiexdContentId();
        }

        HashMap<String, Object> map = new HashMap<>();

        map.put("excludeArticle", ids);

        map.put("limitNum", limit);

        map.put("status",status);

        List<Article> articleList = null;

        articleList = articleMapper.excludeAndLimitQuantities(map);

        if (articleList.size() == 0) {
            return null;
        }
        return articleList;
    }

    @Override
    public Page<ArticleAndCommentAndSubfunction> getPage
            (List<Article> topArticleList, List<Article> fillingArticleList, int page, int number, String fixed_name,int status) {

        List<ArticleAndCommentAndSubfunction> aacas = new ArrayList<>();

        //查总数
        int count = articleMapper.count(status);

        //得到页数
        int pageSum = count / number;

        if(count%number!=0){
            pageSum+=1;
        }

        articlePage.setTotal(count);
        articlePage.setPages(pageSum);

        //单纯是置顶内容情况下
        if (topArticleList != null && topArticleList.size() == number) {
            //当前全部是置顶内容
            for (Article article : topArticleList) {
                ArticleAndCommentAndSubfunction articleAndCommentAndSubfunction = new ArticleAndCommentAndSubfunction();
                articleAndCommentAndSubfunction.setArticle(article);
                aacas.add(articleAndCommentAndSubfunction);
            }
            articlePage.setList(aacas);
            return articlePage;
        }
        //置顶不足情况下
        else if (topArticleList != null && fillingArticleList != null) {
            topArticleList.addAll(fillingArticleList);
            for (Article article : topArticleList) {
                ArticleAndCommentAndSubfunction articleAndCommentAndSubfunction = new ArticleAndCommentAndSubfunction();
                articleAndCommentAndSubfunction.setArticle(article);
                aacas.add(articleAndCommentAndSubfunction);
            }
            articlePage.setList(aacas);
            return articlePage;
        }

        //处理没有置顶和存在置顶外的
        FixedLocation fixedLocation = fixedLocationMapper.selectbyName(fixed_name);

        if (fixedLocation == null) {
            throw new UserInvalidParameterException("固定地点不存在");
        }

        //查询固定地点中状态表内容
        List<BloggerLayout> bloggerLayoutList = bloggerLayoutMapper.selectByFiexdPlace(fixedLocation.getFixedId());

        if (bloggerLayoutList.size() != 0) {

            //获取置顶不足情况下所需补充的数量或者页内所需内容量大于置顶情况下所需补充的数量
            int fillingSum = 0;

            if (number > bloggerLayoutList.size()) {
                fillingSum = number % bloggerLayoutList.size();
            } else if (number < bloggerLayoutList.size() && bloggerLayoutList.size() % number != 0) {
                fillingSum = bloggerLayoutList.size() % number;
            }

            long[] exceptionArticleIds = new long[bloggerLayoutList.size() + fillingSum];

            //查处最新fillingSum条数据
            for (int i = 0; i < bloggerLayoutList.size(); i++) {
                exceptionArticleIds[i] = bloggerLayoutList.get(i).getFiexdContentId();
            }


            if (bloggerLayoutList.size() != exceptionArticleIds.length) {
                Map<String, Object> map = new HashMap<>();
                map.put("excludeArticle", exceptionArticleIds);
                map.put("limitNum", fillingSum);
                map.put("status",status);
                List<Article> articleList = articleMapper.excludeAndLimitQuantities(map);
                for (int i = exceptionArticleIds.length; i < articleList.size() + exceptionArticleIds.length; i++) {
                    exceptionArticleIds[i] = articleList.get(i).getId();
                }
            }

            List<Article> emps = articleMapper.selectAllExcludeArticle(exceptionArticleIds,status);

            page = page - exceptionArticleIds.length / number;

            PageHelper.startPage(page, number);

            PageInfo<Article> pageInfo = new PageInfo<>(emps, number);

            for (Article article : pageInfo.getList()) {
                ArticleAndCommentAndSubfunction articleAndCommentAndSubfunction = new ArticleAndCommentAndSubfunction();
                articleAndCommentAndSubfunction.setArticle(article);
                aacas.add(articleAndCommentAndSubfunction);
            }
            articlePage.setList(aacas);
            return articlePage;
        }
        //不存在置顶情况下
        PageHelper.startPage(page, number);
        List<Article> articleList = articleMapper.getAllArticle(status);
        PageInfo<Article> pageInfo = new PageInfo<>(articleList, number);
        for (Article article : pageInfo.getList()) {
            ArticleAndCommentAndSubfunction articleAndCommentAndSubfunction = new ArticleAndCommentAndSubfunction();
            articleAndCommentAndSubfunction.setArticle(article);
            aacas.add(articleAndCommentAndSubfunction);
        }
        articlePage.setList(aacas);
        return articlePage;


    }
}
