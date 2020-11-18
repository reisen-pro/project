package com.project.spring.aop.dao;

import com.project.spring.aop.entity.IUser;

/**
 * IUser实现类
 */
public class UserDaoXml implements IUser {
    // do save
    @Override
    public void save() {
        System.out.println("userDao do save");
    }
}
