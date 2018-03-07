package sunzk.lambda;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LocalDateTest {

    static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

    public static void oldDate() throws Exception{
        Callable<Date> call = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return sdf.parse("20180105");
            }
        };
        List<Future<Date>> list = new ArrayList<Future<Date>>();
        ExecutorService p = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            list.add(p.submit(call));
        }
        for (Future<Date> l: list) {
            System.out.println(l.get());
        }
    }

    public static void threadLoaclDate() throws Exception{
          final ThreadLocal<DateFormat> df = new ThreadLocal<DateFormat>(){
            @Override
            protected DateFormat initialValue() {
                return new SimpleDateFormat("yyyy-MM-dd");
            }
        };
        Callable<Date> call = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return df.get().parse("2018-01-05");
            }
        };
        List<Future<Date>> list = new ArrayList<Future<Date>>();
        ExecutorService p = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            list.add(p.submit(call));
        }
        for (Future<Date> l: list) {
            System.out.println(l.get());
        }
    }

    public static void LocalDateTest() throws Exception{
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDate date = LocalDate.now();
        LocalDate.parse("2018-05-09",dtf);
        Callable<LocalDate> call = new Callable<LocalDate>() {
            @Override
            public LocalDate call() throws Exception {
                return LocalDate.parse("2018-05-09",dtf);
            }
        };
        List<Future<LocalDate>> list = new ArrayList<Future<LocalDate>>();
        ExecutorService p = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            list.add(p.submit(call));
        }
        for (Future<LocalDate> l: list) {
            System.out.println(l.get());
        }
    }

    public static void test1(){
        LocalDate ld = LocalDate.now();
        LocalDate ld1 = LocalDate.of(2018,11,27);
        System.out.println(ld);
        System.out.println(ld1);

        Instant t1 = Instant.now();
        Instant t2 = Instant.ofEpochSecond(1000);
        System.out.println(t1);
        System.out.println(t2.toEpochMilli());

        Period p = Period.between(ld,ld1);
        System.out.println(p.getMonths());


        Duration d = Duration.between(t1,t2);
        System.out.println(d.getSeconds());


    }

    public static void test2(){
        LocalDateTime ldt = LocalDateTime.now();
        LocalDateTime ldt2 = ldt.plusDays(10);
        System.out.println(ldt2);
        LocalDateTime ldt3 = ldt.withDayOfYear(10);
        System.out.println(ldt3);
        LocalDateTime ldt4 = ldt.with((l) -> {
            LocalDateTime ldt5 = (LocalDateTime)l;
            DayOfWeek d = ldt5.getDayOfWeek();
            if(d.equals(DayOfWeek.FRIDAY)){
                return ldt.plusDays(3);
            }else if(d.equals(DayOfWeek.SATURDAY)){
                return ldt.plusDays(2);
            }else{
                return ldt.plusDays(1);
            }
        });

        System.out.println(ldt.with(TemporalAdjusters.next(DayOfWeek.FRIDAY)));
        System.out.println(ldt4);

    }

    public static void main(String[] args) throws Exception {

        //LocalDateTest.threadLoaclDate();
        //LocalDate localDate = LocalDate.now();
        //LocalDateTest.LocalDateTest();
        LocalDateTest.test2();

    }





}
