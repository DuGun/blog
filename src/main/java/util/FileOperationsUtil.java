package util;

import exception.customException.FileTypeException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Component
public class FileOperationsUtil {

    /**
     * 用于文章上传至服务器
     *
     * @param multipartFileList 文件对象
     * @param path              服务器路径，非所上传的文件的详细路径
     * @param fileType          文件的类型，不需要则输入null
     * @param rename            是否需要重命名
     * @return
     * @throws IOException
     * @throws FileTypeException 文件上传格式不符合所接收格式
     */
    public static String[] uploadFiles(List<MultipartFile> multipartFileList, String path, String fileType, boolean rename) throws FileTypeException, IOException {

        //获取名字
        String fileFullName = null;


        //校验参数
        if (multipartFileList == null || path == null) {
            throw new NullPointerException("文件上传所需参数为null");
        }

        if (multipartFileList.size() == 0) {
            return null;
        }

        if (path.trim().equals("")) {
            throw new IllegalArgumentException("当前path不合法");
        }

        //文件类型，若为null 则接受全部格式
        if (fileType == null || fileType.trim().equals("")) {
            fileType = "*";
        }

        //先查询所有文件是否符合规定格式
        for (int i = 0; i < multipartFileList.size(); i++) {
            //获取文件对象
            MultipartFile multipart = multipartFileList.get(i);

            //获取文件的全称
            fileFullName = multipart.getOriginalFilename();

            //获取原文件后缀
            String filePrefix = FilenameUtils.getExtension(fileFullName);

            //判断是否在支持的格式内
            boolean filePrefixYON = fileType.contains("*") || fileType.contains(filePrefix) || fileType.contains(filePrefix.toUpperCase());
            if (!filePrefixYON) {
                throw new FileTypeException("文件仅支持如下格式:" + fileType);
            }
        }

        File file = new File(path);

        //存储上传的文件名称
        String[] fileNames = new String[multipartFileList.size()];

        //判断当前路径是否存在
        if (!file.exists()) {
            //不存在则创建文件夹
            file.mkdirs();
        }

        SnowFlake snowFlake = new SnowFlake();
        for (int i = 0; i < multipartFileList.size(); i++) {

            //获取文件对象
            MultipartFile multipart = multipartFileList.get(i);

            //获取文件的全称
            fileFullName = multipart.getOriginalFilename();

            File uploadFile = new File(file, fileFullName);

            //是否需要重命名 还是有可能出现重复名出现覆盖的问题出现
            if (rename == true || uploadFile.exists()) {
                String fileName = FilenameUtils.getBaseName(fileFullName);
                String newName = String.valueOf(snowFlake.nextId());
                fileFullName = fileFullName.replace(fileName, newName);
            }

            //上传到服务器指定位置
            try {
                uploadFile = new File(file, fileFullName);
                multipart.transferTo(uploadFile);
                fileNames[i] = fileFullName;
            } catch (IOException e) {
                //对于刚才生产的时间戳文件夹进行删除
                for (String fileUrl : fileNames) {
                    File deleteFile = new File(file, fileUrl);
                    if (deleteFile.exists()) {
                        deleteFile.delete();
                    }
                }
                throw new IOException(e);
            }

        }
        return fileNames;


    }

    public static void deleteFile(String url, boolean deleteMkdir) throws IOException {
        if (url == null || url.trim().equals("")) {
            throw new NullPointerException();
        }

        if (deleteMkdir == true) {
            File file = new File(url);
            if (file.exists()) {
                if (!file.isDirectory()) {
                    throw new IllegalArgumentException("当前URL不是文件夹");
                } else {
                    //删除文件夹
                    FileUtils.forceDelete(new File(url));
                }
            }

        } else {
            File file = new File(url);
            if (file.exists()) {
                if (file.isDirectory()) {
                    throw new IllegalArgumentException("当前URL不是文件");
                } else {
                    file.delete();
                }
            }
        }


    }


}
