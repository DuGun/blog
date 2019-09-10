package service.messages;

import bean.Background;
import exception.customException.FileTypeException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
public interface BackgroundOperations {
    /**
     * 背景图上传至服务器
     *
     * @param backgroundMultipartFiles 背景图文件对象
     * @param url 服务器url
     * @return 文件文件名
     * @throws IOException 文件删除和上传的IO异常
     * @throws FileTypeException 背景图不符合规定格式异常
     */
    String[] uploadArticleFile(List<MultipartFile> backgroundMultipartFiles, String url) throws IOException, FileTypeException;

    /**
     * 背景图
     *
     * @param urls
     * @return
     */
    int uploadBackgroundDatabase(String[] urls );

    /**
     * 设置背景图固定或者背景图级别顺序
     *
     * @param backgroundList 背景图对象List
     * @param whetherFixed  固定嘛？
     * @param fixedName 固定地点
     */
    void fixedBackground(List<Background> backgroundList, boolean whetherFixed, String fixedName);



}
