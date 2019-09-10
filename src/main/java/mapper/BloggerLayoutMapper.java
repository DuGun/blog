package mapper;

import bean.BloggerLayout;

import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public interface BloggerLayoutMapper {
    int deleteByPrimaryKey(BloggerLayout key);

    int insert(BloggerLayout record);

    int insertSelective(BloggerLayout record);

    int insertAll(List<BloggerLayout> newBloggerLayoutList);

    List<BloggerLayout> selectByFiexdPlace(long fixed_id);

    List<BloggerLayout> selectByFiexdPlaceLimit(long fixed_id,int limit);



    BloggerLayout selectByPrimaryKey(BloggerLayout key);

    int updateRankByNameCIdFP(BloggerLayout record);

    int updateByPrimaryKey(BloggerLayout record);


    int deleteFiexdPlaceAll(long fiexd_place);

    int deleteByPrimaryKeyAll(List<BloggerLayout> deleteBloggerLayouts);
}