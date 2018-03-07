package sunzk.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by 82681 on 2017/12/27.
 */
@ComponentScan(basePackages = {"sunzk","com.sun.szk"})
@MapperScan(basePackages = "sunzk.mapper")
@EnableAutoConfiguration
//@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
