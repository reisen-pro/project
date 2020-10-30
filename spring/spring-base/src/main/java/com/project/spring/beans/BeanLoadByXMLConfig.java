package com.project.spring.beans;

import com.project.spring.beans.dao.UserDao;
import com.project.spring.beans.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLoadByXMLConfig {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("application-XMLConfig.xml");
        UserService service = (UserService) ac.getBean("userService");
        service.save();
        // 通过javaConfig配置的userDao
        UserDao userDao = (UserDao) ac.getBean("userDao");
        System.out.println(userDao);

    }
}
