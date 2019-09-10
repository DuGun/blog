package controller.user;


import bean.Words;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.user.WordsRelated;
import util.ResponseBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 脐橙
 * @data 2019-09-05 20:30
 * @Email 847033093@qq.com
 */
@Controller
@RequestMapping("user")
public class WordsRelatedC {
    @Resource
    WordsRelated wordsRelated;

    @Resource
    ResponseBean responseBean;


    /**
     * 用于获取一句话
     *
     * @return 一句话List
     */
    @GetMapping(value = "words/show")
    @ResponseBody
    public ResponseBean showWords(){

        List<Words> wordsList=wordsRelated.getWords();

        responseBean.setData(wordsList);
        responseBean.setCode(ResponseBean.HTTP_CODE_OK);
        responseBean.setMsg(null);
        return responseBean;
    }


}
