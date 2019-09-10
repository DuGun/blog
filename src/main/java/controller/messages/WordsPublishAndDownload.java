package controller.messages;

import bean.Words;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import service.messages.WordsOperations;
import service.user.WordsRelated;
import util.ResponseBean;

import javax.annotation.Resource;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Controller
@RequestMapping("message")
public class WordsPublishAndDownload {

    @Resource
    WordsOperations wordsOperations;

    @Resource
    ResponseBean responseBean;

    /**
     * 用于添加一句话内容
     *
     * @param words
     * @return
     */
    @PostMapping(value = "words/publish",consumes = "application/json")
    @ResponseBody
    public ResponseBean wordsDownload(@RequestBody @Validated Words words){

        wordsOperations.uploadWords(words);

        responseBean.setData(null);
        responseBean.setCode(ResponseBean.HTTP_CODE_OK);
        responseBean.setMsg(null);
        return responseBean;
    }

}
