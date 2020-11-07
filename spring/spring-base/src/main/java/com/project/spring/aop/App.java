package com.project.spring.aop;

import com.project.spring.aop.dao.UserDao;
import com.project.spring.aop.factory.ProxyFactory;

public class App {
    public static void main(String[] args) {
        UserDao userDao = new UserDao();
        System.out.println("userDao:" + userDao.getClass());
        UserDao factory = (UserDao) new ProxyFactory(userDao).getProxyInstance();
        System.out.println("userDaoFactory:" + factory.getClass());
        factory.save();

    }
}
