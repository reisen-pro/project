package com.project.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan(basePackages = {"com.guohualife.ebp.ass.assets.service","com.project.springboot"})
@ImportResource({"classpath:/config/assets/dubboClient.xml"})
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
