package sunzk.Stream;

import sunzk.entity.Employee;
import sunzk.entity.TestUser;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by 82681 on 2018/1/11.
 */
public class TestStream3 {
    static List<Employee> emp = Arrays.asList(
            new Employee("szk",18,"22"),
            new Employee("szk",18,"22"),
            new Employee("szk",18,"22"),
            new Employee("szk",18,"22"),
            new Employee("szk",18,"22"),
            new Employee("szk",18,"22"),
            new Employee("szk",18,"22"),
            new Employee("mz",35,"22"),
            new Employee("mlt",35,"22"),
            new Employee("hlt",38,"22"),
            new Employee("wzb",48,"22"));
    static List<String> str = Arrays.asList("szk","mz", "hlt","hlt","wzb");

    public static  void streamMap(){
        emp.stream().map((e) -> {
            e.setName(e.getName().toUpperCase());
            return e;
        }).filter((e) -> e.getAge()>35)
          .forEach(System.out::println);
        System.out.println("---------------------------------------------------------");
        Stream<Stream<Character>> stream = str.stream().map(TestStream3::getStream);
        stream.forEach((s) -> s.forEach(System.out::println));
    }


    public static void streamMap2(){
        str.stream().flatMap(TestStream3::getStream).forEach(System.out::println);
    }

    public static Stream<Character> getStream(String str){
        List<Character> list = new ArrayList<>();
        for (Character s : str.toCharArray()) {
            list.add(s);
        }
        return list.stream();
    }

    public static void streamMap3(){
        str.stream()
                .sorted()
                .forEach(System.out::println);

        System.out.println("---------------------------------------------------------");
        emp.stream()
                .sorted((e1,e2) -> {
                    if(e1.getAge() == e2.getAge()){
                        return e1.getName().compareTo(e2.getName());
                    }else{
                        return e1.getAge().compareTo(e2.getAge());
                    }
                }).forEach(System.out::println);
    }


    public static void streamMap4(){
       boolean status =  emp.stream()
                .allMatch((e) -> e.getAge()>17);
        System.out.println("allMatch+++++++++++++++++++++++++++++++:" + status);

        boolean status1 =  emp.stream()
                .anyMatch((e) -> e.getAge()>17);
        System.out.println("anyMatch+++++++++++++++++++++++++++++++:" + status1);

        boolean status2 =  emp.stream()
                .noneMatch((e) -> e.getAge()>17);
        System.out.println("noneMatch+++++++++++++++++++++++++++++++:" + status2);

        Optional<Employee> optional =  emp.stream().filter((e) -> e.getAge()>20).findFirst();
        System.out.println("findFurst+++++++++++++++++++++++++++++++:" + optional.get());

        Optional<Employee> optional1 =  emp.parallelStream().filter((e) -> e.getAge()>20).findAny();
        System.out.println("findAny+++++++++++++++++++++++++++++++:" + optional1.get());

        long l = emp.stream().count();
        System.out.println("count+++++++++++++++++++++++++++++++:" + l);

        Optional<Employee> optional2 = emp.stream().filter((e) -> e.getAge()>=35).max(Comparator.comparing(Employee::getAge));
        System.out.println("max+++++++++++++++++++++++++++++++:" + optional2.get());

        Optional<Employee> optional3 = emp.stream().filter((e) -> e.getAge()<=35).min((e1,e2) -> {
            if(e1.getAge() == e2.getAge()){
                return e1.getName().compareTo(e2.getName());
            }else{
                return e1.getAge().compareTo(e2.getAge());
            }
        });
        System.out.println("min+++++++++++++++++++++++++++++++:" + optional3.get());

    }


    public static void streamMap5(){
      Optional<Integer> o =   emp.stream()
                .map(Employee::getAge)
                .reduce((integer, integer2) -> integer + integer2);

        System.out.println("---------------------------------------------------------" + o.get());

        emp.stream()
                .map(Employee::getAge)
                .reduce(10,(i,i1) -> i+i1);
//        emp.stream()
//                .sorted((e1,e2) -> {
//                    if(e1.getAge() == e2.getAge()){
//                        return e1.getName().compareTo(e2.getName());
//                    }else{
//                        return e1.getAge().compareTo(e2.getAge());
//                    }
//                }).forEach(System.out::println);
    }

    public static void streamMap6(){
        List<String> list = emp.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        list.stream()
                .forEach(System.out::println);
        System.out.println("---------------------------------------------------------" );

    }

    public static void streamCollect(){
        Set<String> list = emp.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        list.stream()
                .forEach(System.out::println);
        System.out.println("set---------------------------------------------------------" );

        Set<String> list1 = emp.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(HashSet::new));
        list1.stream()
                .forEach(System.out::println);
        System.out.println("---------------------------------------------------------" );

        long l = emp.stream()
                .map(Employee::getName)
                .collect(Collectors.counting());
        System.out.println(l );
        System.out.println("count---------------------------------------------------------" );

        Double d = emp.stream()
                .map(Employee::getAge)
                .collect(Collectors.averagingInt(value -> value));
        System.out.println(l );
        System.out.println("avg---------------------------------------------------------" );

       int i = emp.stream()
                //.map(Employee::getAge)
                .collect(Collectors.summingInt(Employee::getAge));
        System.out.println(i);
        System.out.println("sum---------------------------------------------------------" );

        DoubleSummaryStatistics d1 = emp.stream()
                .collect(Collectors.summarizingDouble(Employee::getAge));

        System.out.println(d1.getMax());
        System.out.println("max---------------------------------------------------------" );

        Optional<Employee> o = emp.stream()
                .collect(Collectors.maxBy(Comparator.comparing(Employee::getAge)));
        System.out.println(o.get());
        System.out.println("maxby---------------------------------------------------------" );

        Optional<Employee> o1 = emp.stream()
                .collect(Collectors.minBy(Comparator.comparing(Employee::getAge)));
        System.out.println(o1.get());
        System.out.println("minby---------------------------------------------------------" );

        String s = emp.stream()
                .map(Employee::getName)
                .collect(Collectors.joining());
        System.out.println(s);
        System.out.println("join---------------------------------------------------------" );

        Map<String,Map<String,List<Employee>>> map = emp.stream()
                .collect(Collectors.groupingBy(Employee::getName,Collectors.groupingBy((e) -> {
                    if(((Employee)e).getAge() < 17){
                        return "少年";
                    }else{
                        return "青年";
                    }
                })));
        System.out.println(map);
        System.out.println("group---------------------------------------------------------" );

        Map<Boolean,List<Employee>> part = emp.stream()
                .collect(Collectors.partitioningBy((e) -> e.getAge() > 17));
        System.out.println(part);
        System.out.println("part---------------------------------------------------------" );

    }

    public static void test(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        List<Integer> list1 = list.stream()
                .map((i) -> i*i)
                .collect(Collectors.toList());
        list1.stream().forEach(System.out::println);

        System.out.println("---------------------------------------------------------" );

        Optional<Integer> i =emp.stream()
                .map((e) -> 1)
                .reduce(Integer::sum);
        System.out.println(i.get());
    }

    public static void forkjoin(){
        Instant start = Instant.now();
        LongStream.rangeClosed(0,1000000000000L)
                .parallel()
                .reduce(0,Long::sum);
        Instant end = Instant.now();
       System.out.print( Duration.between(start,end).toMillis());
    }

    public static void OptionalTest(){
        Optional<TestUser> op1 = Optional.ofNullable(null);
        Optional<Employee> op2 = Optional.empty();
        Optional<Employee> op3 = Optional.ofNullable(null);
        op3.orElse(new Employee()).getAge();
        System.out.print(getName1(op1));

    }
    public static String getName1(Optional<TestUser> t){
       return  t.orElse(new TestUser())
                   .getEmp()
                   .orElse(new Employee("苍老师"))
                   .getName();
    }




    public static void main(String[] args) {
//        TestStream3.streamMap();
//        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
//        TestStream3.streamMap2();
        //TestStream3.streamMap6();
        //TestStream3.streamCollect();
        //TestStream3.forkjoin();
        TestStream3.OptionalTest();

    }

}
