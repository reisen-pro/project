package com.project.spring.di;

import com.project.spring.di.service.UserServiceDi;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanDi {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("application-di.xml");
        UserServiceDi userService = (UserServiceDi) ac.getBean("userService");
        userService.save();
    }
}
