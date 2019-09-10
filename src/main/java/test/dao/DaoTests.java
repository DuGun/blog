package test.dao;


import bean.*;
import mapper.ArticleMapper;
import mapper.BloggerLayoutMapper;
import mapper.ClassifyMapper;
import mapper.FixedLocationMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.messages.BackgroundOperations;
import service.user.ArticleRelated;
import service.user.BloggerLayoutRelated;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/springMyBatis.xml"})
public class DaoTests {


    @Resource
    ArticleMapper articleMapper;

    @Resource
    FixedLocationMapper fixedLocationMapper;

    @Resource
    BloggerLayoutMapper bloggerLayoutMapper;

    @Resource
    BloggerLayoutRelated bloggerLlayoutRelated;

    @Resource
    ArticleRelated articleShowRelated;

    @Resource
    ClassifyMapper classifyMapper;

    @Resource
    BackgroundOperations backgroundOperations;


    @Test
    public void show() {
        Article article = null;
        String contentUrl = null;
        String thumbnailUrl = null;

        article = new Article();
        article.setStatus(0);
        article.setClassifyId(0);
        article.setTitle("测试");

        long id = 363080482914840577l;

        for (int i = 1; i < 40; i++) {
            article.setId(id + (i * 10));
            System.out.println(article.getId());
            article.setContentUrl(String.valueOf(i * 10));
            article.setThumbnailUrl(String.valueOf(i * 10));
            articleMapper.insertSelective(article);
        }

    }


    @Test
    public void show2() {

        //固定地点
        String fixed_name = "arctile_home";

        //地点所需的内容数量
        int fiexd_location_size = 8;

        //首先查询固定地点是否存在
        FixedLocation fixedLocation = fixedLocationMapper.selectbyName(fixed_name);

        //若固定地点存在，查询出固定地点的内容，按固定等级顺序来查询出来
        if (fixedLocation != null) {
            //固定地点的内容
            List<BloggerLayout> bloggerLayoutList = null;

            bloggerLayoutList = bloggerLayoutMapper.selectByFiexdPlace(fixedLocation.getFixedId());

            //用来查询固定之外的内容
            List<Article> articleslist = null;

            //查询出来的固定内容的对象的ID数组
            long[] aarticleIds = new long[bloggerLayoutList.size()];

            if (bloggerLayoutList.size() != 0) {
                for (int i = 0; i < bloggerLayoutList.size(); i++) {
                    aarticleIds[i] = bloggerLayoutList.get(i).getFiexdContentId();
                }

                HashMap<String, Object> map = new HashMap<>();

                map.put("excludeArticle", aarticleIds);

                map.put("limitNum", fiexd_location_size - bloggerLayoutList.size());

                articleslist = articleMapper.excludeAndLimitQuantities(map);

            }

            //查询出来的固定内容的对象
            List<Article> topArticleList = articleMapper.selectByIds(aarticleIds);

            //合并固定和非固定内容
            if (aarticleIds != null) {
                topArticleList.addAll(articleslist);
            }

        }


        //存在置顶情况下，先不使用分页而使用普通方式获取置顶内容

        //判断置顶内容是否达到8条，不够就获取除去置顶之外补足8条信息

        //

//        Map<String, Object> map = new HashMap<>();
//
//        //引入分页查询，使用PageHelper分页功能
//        //在查询之前传入当前页，然后多少记录
//        Integer pn = 1;
//        PageHelper.startPage(pn, 5);
//        //startPage后紧跟的这个查询就是分页查询
//        List<Article> emps = articleMapper.getAllArticle();
//        //使用PageInfo包装查询结果，只需要将pageInfo交给页面就可以
//        PageInfo pageInfo = new PageInfo<>(emps, 5);
//        //pageINfo封装了分页的详细信息，也可以指定连续显示的页数
//
//        map.put("pageInfo", pageInfo);
//
//        System.out.println(1234);

    }

    @Test
    public void xixixi() {

        String fixed_name = "arctile_home";

        int limit = 8;

        int page = 1;

//        //获取置顶内容
//        //先查询该
//        FixedLocation fixedLocation = fixedLocationMapper.selectbyName(fixed_name);
//
//        //获取置顶的文章List
//        List<Article> topArticleList = new ArrayList<>();
//
//        if (fixedLocation == null) {
//            throw new FixedLocationException("固定地点不存在");
//        }
//
//        //查询固定地点中状态表内容
//
//        List<BloggerLayout> bloggerLayoutList = bloggerLayoutMapper.selectByFiexdPlace(fixedLocation.getFixedId());
//
//        if (bloggerLayoutList.size() > 0) {
//
//            if (bloggerLayoutList.size() > limit) {
//                int startIndex = 0;
//                if (page != 1) {
//                    startIndex = (page - 1) * limit ;
//                }
//
//                if (startIndex < 0) {
//                    startIndex = 0;
//                }
//                int number = limit;
//                if ((limit * page) > bloggerLayoutList.size()) {
//                    if (bloggerLayoutList.size() % (limit * page) != 0) {
//                        number = bloggerLayoutList.size() % limit;
//                    }
//                }
//                for (int i = startIndex; i < startIndex + number; i++) {
//                    Article article = articleMapper.selectByPrimaryKey(bloggerLayoutList.get(i).getFiexdContentId());
//                    if (article != null) {
//                        topArticleList.add(article);
//                    }
//                }
//            } else {
//                //存在置顶内容
//                for (BloggerLayout bloggerLayout : bloggerLayoutList) {
//                    Article article = articleMapper.selectByPrimaryKey(bloggerLayout.getFiexdContentId());
//                    if (article != null) {
//                        topArticleList.add(article);
//                    }
//                }
//            }
//
//        }
        System.out.println(12345);

    }

    @Test
    public void opo() {

        Classify classify = new Classify();
        classify.setId(0);
        classify.setClassifyName("不分类");

        try {
            classifyMapper.insertSelective(classify);
        } catch (DuplicateKeyException e) {

            System.out.println(12345432);
            //出现唯一性问题则为分类名字出现重复
            //因为ID 出现重复的几率极低
        } catch (DataIntegrityViolationException exception) {
            System.out.println(0000000);
        }


    }

    @Test
    public void qaqqq() {
//        List<Background> backgroundList = new ArrayList<>();
//        Background background = new Background();
//        Background background2 = new Background();
//        Background background3 = new Background();
//        Background background4 = new Background();
//        Background background5 = new Background();
//        Background background6 = new Background();
//
//
//        background.setId(366965464729137152L);
//        background2.setId(366965464729137153L);
//        background3.setId(366965464729137154L);
//        background4.setId(366965539563909120L);
//        background5.setId(366965539563909121L);
//        background6.setId(366965539563909122L);
//
//        background.setUrl("/blogResources/personalInformation/366965463697338368.jpg");
//        background2.setUrl("/blogResources/personalInformation/366965463697338369.jpg");
//        background3.setUrl("/blogResources/personalInformation/366965463701532672.jpg");
//        background4.setUrl("/blogResources/personalInformation/366965538813128704.jpg");
//        background5.setUrl("/blogResources/personalInformation/366965538813128705.jpg");
//        background6.setUrl("/blogResources/personalInformation/366965538813128706.jpg");
//
//        boolean whetherFixed = false;

        String fixedName = "blog_background";
//
//
//        backgroundList.add(background4);
//        backgroundList.add(background5);
//        backgroundList.add(background6);
//        backgroundList.add(background);
//        backgroundList.add(background2);
//        backgroundList.add(background3);
//
//        backgroundOperations.fixedBackground(backgroundList, whetherFixed, fixedName);


        System.out.println(123456);


    }
}
