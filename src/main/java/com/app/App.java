package com.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by 82681 on 2017/12/27.
 */
@ComponentScan(basePackages = {"sunzk","com"})
//@MapperScan(basePackages = {"sunzk.mapper","com.sun.szk.dao"})
@EnableAutoConfiguration(exclude={
        JpaRepositoriesAutoConfiguration.class//禁止springboot自动加载持久化bean
})
//@SpringBootApplication
@MapperScan(basePackages = {"sunzk.mapper","com.sun.szk.dao"})
//@Configuration
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
