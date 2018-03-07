package sunzk.Stream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sunzk.entity.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by 82681 on 2018/1/10.
 */
@Controller
public class TestStream {


    @RequestMapping("/stream")
    public void createStream(){
        //collection系列集合提供得stream
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        //Arrays中的静态方法stream
        Employee[] s = new Employee[10];
        Stream<Employee> stream1 = Arrays.stream(s);

        //Stream中的of方法
        Stream<String> stream2 = Stream.of("aaa","bbb","ccc");

        //无限流stream
        //迭代
        Stream stream3 = Stream.iterate(2,x -> x+10);
        stream3.limit(10).forEach(System.out::println);
        //生成
        Stream stream4 = Stream.generate(() -> Math.random());
        stream4.limit(10).forEach(System.out::println);
    }


}
