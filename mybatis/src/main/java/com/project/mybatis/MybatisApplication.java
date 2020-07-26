package com.project.mybatis;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Reisen
 */

/**
 *  druid监控页面是一个servlet，需要让SpingBoot支持servlet.在程序入口添加注解 @ServletComponentScan
 */
@SpringBootApplication
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

}
