package sunzk.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import sunzk.entity.TestUser;
import sunzk.mapper.UserMapper;


/**
 * Created by 82681 on 2017/12/27.
 */
@Controller
public class HelloController {

    private static Logger log = Logger.getLogger(HelloController.class);

    @Value("${getValue}")
    private String getValue;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/hello")
    public ModelAndView hello(){
        ModelAndView model = new ModelAndView("index");
        TestUser user = userMapper.getTestUser("aaa");
        model.addObject("user",user);
        return model;
    }

    @RequestMapping("/hello1")
    @ResponseBody
    public String hello1(){
        log.info("@@@@@@@jjdhakjdhksjadhkjahdkja**********");
        //int i = 1/0;
        return "hello1";
    }
    @RequestMapping("/getValue")
    @ResponseBody
    public String getValue(){
        return  getValue;
    }


}
