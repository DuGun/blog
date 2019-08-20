package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import util.ResponseBean;
import util.SnowFlake;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;


@Controller
public class ControllerDemo {

    @Resource
    ResponseBean responseBean;


    @GetMapping("test")
    @ResponseBody
    public ResponseBean show(String name,int time) throws InterruptedException {

        Thread.sleep(time);



        HashMap hashMap=new HashMap();
        hashMap.put("name","猪猪");
        hashMap.put("age",18);
        hashMap.put("Arraysss", Arrays.asList("w","d","g"));

        responseBean.setData(hashMap);
        return  responseBean;
    }



    @GetMapping("message/test")
    @ResponseBody
    public String show2(){

        SnowFlake snowFlake = new SnowFlake(2, 3);

//            String id=snowFlake.nextId();

        return  "管理员";
    }

//
//    @Test
//    public void show2(){
//        ApplicationContext application= new ClassPathXmlApplicationContext("classpath:spring/springMyBatis.xml");
//        CeShi ceShi= (CeShi) application.getBean("ceShi");
//        ceShi.show();
//    }


}
