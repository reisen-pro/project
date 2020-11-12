package com.project.spring.aop.dao;

import com.project.spring.aop.entity.IUser;
import org.springframework.stereotype.Component;

/**
 * IUser实现类
 */
@Component
public class UserDao implements IUser {
    // do save
    @Override
    public void save() {
        System.out.println("userDao do save");
    }
}
