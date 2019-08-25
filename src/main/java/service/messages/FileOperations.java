package service.messages;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface FileOperations {
    Map<String,String>  releaseArticleFile(MultipartFile articleFile, List<MultipartFile> articleContentFiles, MultipartFile thumbnailFile, StringBuilder projectUrl) throws IOException;

}
