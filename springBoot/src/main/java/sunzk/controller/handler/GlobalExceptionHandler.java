package sunzk.controller.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 82681 on 2017/12/27.
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,Object> exceptionHandler(){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("code",500);
        map.put("msg","傻逼出错了");
        return  map;
    }

}
