package com.project.spring.beans.config;

import com.project.spring.beans.dao.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通过java代码来配置bean
 * 通过ImportResource引入xml文件 @ImportResource(value = {"classpath:application-bean.xml"})
 * 在xml中使用<bean></bean>节点
 *
 * @author reisen
 */
@Configuration
public class UserBeanConfiguration {

    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        System.out.println("configuration中创建的" + userDao);
        return userDao;
    }

}
