package sunzk.Stream;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sunzk.entity.Employee;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by 82681 on 2018/1/10.
 */
@Controller
public class TestStream1 {

    List<Employee> emp = Arrays.asList(
            new Employee("szk",18,"22"),
            new Employee("mz",35,"22"),
            new Employee("hlt",38,"22"),
            new Employee("hlt",38,"22"),
            new Employee("wzb",48,"22"));

    @RequestMapping("testStream")
    public void test(){
        Stream<Employee> stream = emp.stream();
        stream.filter(s -> s.getAge()>30)
                .limit(2)
                .forEach(System.out::println);

        System.out.println("-----------------------------------------------------------");

        Stream<Employee> stream1 = emp.stream();
        stream1.filter(s -> s.getAge()>30)
                .skip(1)
                .forEach(System.out::println);

        System.out.println("-----------------------------------------------------------");

        Stream<Employee> stream2 = emp.stream();
        stream2.filter(s -> s.getAge()>30)
                .distinct()
                .forEach(System.out::println);


    }
}
