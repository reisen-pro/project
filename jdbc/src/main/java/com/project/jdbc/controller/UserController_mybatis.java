package com.project.jdbc.controller;

import com.project.jdbc.entity.User;
import com.project.jdbc.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Reisen
 */
@RestController
public class UserController_mybatis {
    @Autowired
    private UserMapper dao;

    /**
     * [{"id":1,"username":"Reisen","password":"1234qwer!!","createDate":"2020-05-24T01:46:41","modifyDate":"2020-05-24T01:46:46"}]
     * localDateTime 会显示一个T不符合观看习惯
     * @see com.project.jdbc.config.LocalDateTimeConfig
     */

    @GetMapping(value = "findAll")
    public List<User> userList() {
        return dao.userList();
    }

    @PostMapping(value = "find/{id}")
    public User user(@PathVariable Long id) {
        return dao.findById(id);
    }

    @PatchMapping(value = "updatePwd/{password}/{id}")
    public void updatePwdById(@PathVariable String password, @PathVariable Long id) {
        dao.updatePwdById(password, id);
    }
}
