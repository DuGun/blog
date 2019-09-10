package serviceImpl.messages;

import bean.Background;
import bean.BloggerLayout;
import bean.FixedLocation;
import exception.customException.FileTypeException;
import exception.customException.SystemInvalidParameterException;
import exception.customException.UserInvalidParameterException;
import mapper.BackgroundMapper;
import mapper.BloggerLayoutMapper;
import mapper.FixedLocationMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import service.messages.BackgroundOperations;
import sun.swing.BakedArrayList;
import util.DatabaseParameter;
import util.FileOperationsUtil;
import util.SnowFlake;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Service
public class BackgroundOperationsImpl implements BackgroundOperations {

    @Resource
    private FileOperationsUtil fileOperationsUtil;

    @Value("${projectFile_blogger_file}")
    private String blogger_file;

    @Value("${projectFile_blogger_img_type}")
    private String blogger_img_type;

    @Resource
    private BackgroundMapper backgroundMapper;

    @Resource
    SnowFlake snowFlake;

    @Resource
    FixedLocationMapper fixedLocationMapper;

    @Value("${data_table_blogger_layout_background}")
    private String data_table_blogger_layout_background;

    @Resource
    BloggerLayoutMapper bloggerLayoutMapper;

    @Override
    public String[] uploadArticleFile(List<MultipartFile> backgroundMultipartFiles, String url) throws IOException, FileTypeException {

        if (backgroundMultipartFiles == null || backgroundMultipartFiles.size() == 0) {
            throw new UserInvalidParameterException("参数错误：背景文件为空");
        }

        if (url == null || url.trim().equals("")) {
            throw new SystemInvalidParameterException("上传背景文件路径存在问题！");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url).append(blogger_file);

        String[] urls = null;

        if (backgroundMultipartFiles != null && backgroundMultipartFiles.size() != 0) {
            try {
                urls = fileOperationsUtil.uploadFiles(backgroundMultipartFiles, stringBuilder.toString(), blogger_img_type, true);
            } catch (FileTypeException e) {
                throw new FileTypeException("背景图文件仅支持如下格式:" + blogger_img_type);
            }
        }

        for (int i = 0; i < urls.length; i++) {
            urls[i] = blogger_file + urls[i];
        }
        return urls;
    }

    @Override
    public int uploadBackgroundDatabase(String[] urls) {


        List<Background> backgroundList = new ArrayList<>();

        for (String url : urls) {
            Background background = new Background();
            background.setUrl(url);
            background.setId(snowFlake.nextId());
            backgroundList.add(background);
        }

        //批量插入数据库
        int sum = 0;

        sum = backgroundMapper.insertSelectiveAll(backgroundList);

        return sum;
    }

    //设置指定和顺序
    @Override
    public void fixedBackground(List<Background> backgroundList, boolean whetherFixed, String fixedName) {

        if (backgroundList == null || fixedName == null) {
            throw new NullPointerException();
        }

        if (backgroundList.size() == 0) {
            throw new UserInvalidParameterException("参数有误：背景参数为0");
        }

        if (fixedName.trim().equals("")) {
            throw new UserInvalidParameterException("背景图指定固定地点不存在");
        }

        FixedLocation fixedLocation = fixedLocationMapper.selectbyName(fixedName);

        if (fixedLocation == null) {
            throw new UserInvalidParameterException("背景图指定固定地点不存在");
        }

        List<BloggerLayout> existingBloggerLayoutList = bloggerLayoutMapper.selectByFiexdPlace(fixedLocation.getFixedId());


        //根据参数查看是否设置固定，如果设置固定则将非固定的删除
        if (whetherFixed == true) {
            if (backgroundList.size() > 1) {
                throw new UserInvalidParameterException("参数有误：设置固定情况下只许设置单张背景图");
            }

            if (existingBloggerLayoutList.size() > 0) {
                BloggerLayout bloggerLayout = existingBloggerLayoutList.get(0);

                if (bloggerLayout.getFiexdRank() == DatabaseParameter.FIEXD_RANK_FIXED && bloggerLayout.getFiexdContentId() == backgroundList.get(0).getId()) {
                    throw new UserInvalidParameterException("参数有误：当前背景图已为固定状态");
                } else {
                    //删除固定或其他状态内容
                    bloggerLayoutMapper.deleteFiexdPlaceAll(fixedLocation.getFixedId());
                }
            }
            //设置固定内容
            BloggerLayout newBloggerLayout = new BloggerLayout();
            newBloggerLayout.setTableName(data_table_blogger_layout_background);
            newBloggerLayout.setFiexdContentId(backgroundList.get(0).getId());
            newBloggerLayout.setFiexdPlace(fixedLocation.getFixedId());
            newBloggerLayout.setFiexdRank(DatabaseParameter.FIEXD_RANK_FIXED);
            bloggerLayoutMapper.insertSelective(newBloggerLayout);

        } else {

            if (backgroundList.size() > DatabaseParameter.FIEXD_RANK_LONGST) {
                throw new UserInvalidParameterException("背景设置顺序最多允许" + DatabaseParameter.FIEXD_RANK_LONGST + "张");
            }

            //删除全部内容
            bloggerLayoutMapper.deleteFiexdPlaceAll(fixedLocation.getFixedId());

            ArrayList<BloggerLayout> bloggerLayouts = new ArrayList<>();

            //插入新内容
            for (int i = 0; i < backgroundList.size(); i++) {
                BloggerLayout bloggerLayout = new BloggerLayout();
                //顺序级别
                int rank = 0;
                rank = DatabaseParameter.queryLevel(i + 1, false);
                bloggerLayout.setFiexdPlace(fixedLocation.getFixedId());
                bloggerLayout.setTableName(data_table_blogger_layout_background);
                bloggerLayout.setFiexdRank(rank);
                bloggerLayout.setFiexdContentId(backgroundList.get(i).getId());
                bloggerLayouts.add(bloggerLayout);

            }
            bloggerLayoutMapper.insertAll(bloggerLayouts);
        }
    }


}
