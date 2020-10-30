package com.project.spring.beans.service;

import com.project.spring.beans.dao.UserDao;
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

    @Resource(name = "userDao")
    private UserDao userDao;

    public UserService(List<User> userList) {
        this.userList = userList;
    }

    public void save() {
        userDao.save();
    }
}
