package exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExceptionParameterUtil {

    @Value("${projectFile_thumbnail_type}")
    private String imgType;

    @Value("${projectFile_article_type}")
    private String htmlType;

    public String getImgErrorMessage() {
        return  "图片格式不在接收范围，仅支持以下格式:" + imgType;
    }

    public String getHtmlTypeErrorMessage() {
        return  "文章格式不在接收范围，仅支持以下格式:" + htmlType;
    }

}
