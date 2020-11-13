package com.project.spring.aop;

import com.project.spring.aop.dao.OrderDao;
import com.project.spring.aop.dao.UserDao;
import com.project.spring.aop.entity.IUser;
import com.project.spring.aop.factory.ProxyFactoryConstruction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        System.out.println("userDao:" + userDao.getClass());
        UserDao factory = (UserDao) new ProxyFactoryConstruction(userDao).getProxyInstance();
        System.out.println("userDaoFactory:" + factory.getClass());
        factory.save();
    }

    @Test
    public void testAopXmlConfig() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("application-aop.xml");
        IUser iUser = (IUser) ac.getBean("proxy");
        System.out.println(iUser.getClass());
        iUser.save();
    }

    @Test
    public void testAspectj() {
        ApplicationContext ac = new ClassPathXmlApplicationContext("application-aopConfig.xml");
        IUser iUser = (IUser) ac.getBean("userDao");
        OrderDao orderDao = (OrderDao) ac.getBean("orderDao");
        // jdk代理
        System.out.println(iUser.getClass());
        // cglib动态代理
        System.out.println(orderDao.getClass());
        //iUser.save();
        orderDao.save();
    }
}
