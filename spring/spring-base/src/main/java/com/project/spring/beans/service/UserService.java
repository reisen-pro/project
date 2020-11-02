package com.project.spring.beans.service;

import com.project.spring.beans.dao.UserDao;
import com.project.spring.beans.entity.DaoFactory;
import com.project.spring.beans.entity.DaoFactoryByConfig;
import com.project.spring.beans.entity.User;
import lombok.Data;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author reisen
 * 定义一个用户服务
 */
@Data
@Service
public class UserService {
    List<User> userList;

    /*
    自动注入的方式
    @Resource(name = "userDao")
    private UserDao userDao;
    */

    /*
    通过反射进行调用
    private UserDao userDao = DaoFactory.getInstance().createDao("com.project.spring.beans.dao.UserDao",UserDao.class);
    */

    //
    private UserDao userDao = DaoFactoryByConfig.getInstance().createUserDao();

    public UserService(List<User> userList) {
        this.userList = userList;
    }

    public void save() {
        userDao.save();
    }
}
