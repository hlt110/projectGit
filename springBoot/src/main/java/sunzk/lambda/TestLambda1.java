package sunzk.lambda;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sunzk.entity.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * Created by 82681 on 2018/1/9.
 */
@Controller
@RequestMapping("/lambda")
public class TestLambda1 {

    @RequestMapping("/index")
    public ModelAndView getValue(){
        ModelAndView model = new ModelAndView("lambda");
        //四大内置函数式接口
        happy(10000,s -> System.out.print("####################################" + s));
        List<String> randomList = get(10,() -> Math.random() * 100 + "");
        String str = handler("abnchjk1234",(s) -> s.substring(0,2).toUpperCase() + s.substring(2,s.length()));
        List<String> list = Arrays.asList("szk","mzsb","wzbsb","hltdsb","mz");
        List<String> list1 = test(list,(s) -> s.length() > 3);
        //方法引用
        Consumer<String> c = System.out::println;
        c.accept("点卡换大号的卡号的空间哈看大家哈看觉得");
        Employee emp = new Employee();
        Supplier<String> s = emp::getName;
        String name = s.get();
        BiPredicate<String,String> bp = String::equals;
        boolean b = bp.test("aaa","bbb");


        model.addObject("list",randomList);
        model.addObject("list1",list1);
        model.addObject("value",str);
        model.addObject("name",name);
        return model;
    }



    public List<String> test(List<String> l, Predicate<String> p){
        List<String> list = new ArrayList<>();
        for (String str : l) {
            if(p.test(str)){
                list.add(str);
            }
        }
        return list;
    }

    public String handler(String str, Function<String,String> f){
        return f.apply(str);
    }

    public List<String> get (int num, Supplier<String> s){
        List<String> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
             list.add(s.get());
        }
        return list;
    }

    public void happy(double d,Consumer<Double> c){
        c.accept(d);
    }
}
