package sunzk.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Created by 82681 on 2017/12/27.
 */
@RestController
public class TestController {
    @RequestMapping("/helloTest")
    public String hello(){
        return "Hello World Test";
    }

    public static void main(String[] args){
        String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        int length = 15;
        Random random = new Random();
        for (int i = 0; i <6 ; i++) {
            int j = random.nextInt(5);
            System.out.println(j);
        }
        int j = random.nextInt(5);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        System.out.println(sb.toString());// 6FUCPy9hCJRbBSg
    }
}
