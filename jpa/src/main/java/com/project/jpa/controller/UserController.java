package com.project.jpa.controller;

import com.project.jpa.dao.UserDao;
import com.project.jpa.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserDao userDao;

    /**
     * [{"id":1,"username":"Reisen","password":"1234qwer!!","createDate":"2020-05-24T01:46:41","modifyDate":"2020-05-24T01:46:46"}]
     * localDateTime 会显示一个T不符合观看习惯
     * @see com.project.mybatis.config
     */

    @GetMapping(value = "findAll")
    public List<User> userList() {
        return userDao.findAll();
    }

    @GetMapping(value = "find/{id}")
    public User user(@PathVariable Long id) {
        return userDao.findById(id).orElse(new User());
    }

    @PatchMapping(value = "updatePwd/{password}/{id}")
    public void updatePwdById(@PathVariable String password, @PathVariable Long id) {
        userDao.updatePwdById(password, id);
    }
}
