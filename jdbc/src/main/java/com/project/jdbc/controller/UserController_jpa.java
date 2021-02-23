package com.project.jdbc.controller;

import com.project.jdbc.dao.UserDao_jpa;
import com.project.jdbc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class UserController_jpa {
    @Autowired
    @Qualifier("userDao_jpa")
    private UserDao_jpa userDao;

    /**
     * [{"id":1,"username":"Reisen","password":"1234qwer!!","createDate":"2020-05-24T01:46:41","modifyDate":"2020-05-24T01:46:46"}]
     * localDateTime 会显示一个T不符合观看习惯
     * @see com.project.jdbc.config.LocalDateTimeConfig
     */

    @GetMapping(value = "jpa/findAll")
    public List<User> userList() {
        return userDao.findAll();
    }

    @GetMapping(value = "jpa/find/{id}")
    public User user(@PathVariable Long id) {
        return userDao.findById(id).orElse(new User());
    }

    @PatchMapping(value = "jpa/updatePwd/{password}/{id}")
    public void updatePwdById(@PathVariable String password, @PathVariable Long id) {
        userDao.updatePwdById(password, id);
    }
}
