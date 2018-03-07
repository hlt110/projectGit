package sunzk.lambda;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import sunzk.entity.Employee;
import sunzk.entity.TestUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 82681 on 2018/1/5.
 */
@Controller
public class TestLambda {

    @RequestMapping("/lambda1")
    public ModelAndView index(){
        ModelAndView model = new ModelAndView("index");
        List<TestUser> list = new ArrayList<TestUser>();
        List<TestUser> newList = getList(list,(u) -> u.getAge() > 100);

        list.stream()
                .filter((u) -> u.getAge() > 100)
                .limit(2)
                .forEach(System.out::println);






        return model;
    }


    @RequestMapping("/lambda2")
    public ModelAndView employee(){
        ModelAndView model = new ModelAndView("lambda");
        Employee e = new Employee();
        e.setAge(15);
        e.setName("szk");
        e.setPhone("123456");
        Employee e1 = new Employee();
        e1.setAge(12);
        e1.setName("wzb");
        e1.setPhone("654321");
        List<Employee> emp = new ArrayList<>();
        emp.add(e);
        emp.add(e1);
        List<Employee> newList = getList1(e,e1,(e2,e3) -> {
            List<Employee> list =  new ArrayList<>();
            if(e2.getAge() > e3.getAge()){
                list.add(e3);
                list.add(e2);
            }else{
                list.add(e2);
                list.add(e3);
            }
           return list;
        });
        model.addObject("list",newList);
        return model;

    }

    @RequestMapping("/lambda3")
    public ModelAndView employee1(){
        ModelAndView model = new ModelAndView("lambda");
        Employee e = new Employee();
        e.setAge(18);
        e.setName("szk");
        e.setPhone("123456");
        Employee e1 = new Employee();
        e1.setAge(12);
        e1.setName("hlt");
        e1.setPhone("654321");
        Employee e2 = new Employee();
        e1.setAge(18);
        e1.setName("mz");
        e1.setPhone("321654");
        List<Employee> emp = new ArrayList<>();
        emp.add(e);
        emp.add(e1);
        emp.add(e2);

        Collections.sort(emp,(o1,o2) -> {
            if(o1.getAge() == o2.getAge()){
                return  o1.getName().compareTo(o2.getName());
            }else{
                return  o1.getAge().compareTo(o2.getAge());
            }
        });
        model.addObject("lsit",emp);
        return model;
    }


    private List<Employee> getList1(Employee e,Employee e1, FilterEmployee<Employee> t){
        return t.sort(e,e1);

    }

    private List<TestUser> getList(List<TestUser> user, FilterUser<TestUser> t){
        List<TestUser> newList = new ArrayList<TestUser>();
        for (TestUser u : user) {
            if(t.test(u)){
                newList.add(u);
            }
        }
        return newList;
    }


    @RequestMapping("/getLambdaValue")
    public ModelAndView getValue(){
        ModelAndView model = new ModelAndView("lambda");

        String str = getValue1("abc123",s -> {return s.substring(0,1).toUpperCase()+s.substring(1,s.length());});
        model.addObject("value",str);
        return model;
    }

    public String getValue1(String str,FilterGetValue g){
        return g.getValue(str);
    }

    @RequestMapping("/getLambdaSum")
    public ModelAndView getSum(){
        ModelAndView model = new ModelAndView("lambda");

        String str = getValue2(2,5,(l1,l2) -> String.valueOf(l1 * l2));
        model.addObject("value",str);
        return model;
    }

    public String getValue2(long l1,long l2,FilterMyFunction<Long,String> g){
        return g.getSum(l1,l2);
    }

}
