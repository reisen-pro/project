package com.project.jdbc.controller;

import com.project.jdbc.dao.UserDao;
import com.project.jdbc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Reisen
 */
@RestController
public class UserController {
    @Autowired
    @Qualifier("userDao")
    private UserDao dao;

    /**
     * QUERY USER BY ID
     *
     * @param id ID
     * @return USER
     */
    @GetMapping(value = "user/{id}")
    public User queryUserById(@PathVariable String id) {
        return dao.findById(Long.parseLong(id)).orElse(new User());
    }

    /**
     * 新增一个用户/更新一个用户
     * 如果user中带上了主键，则是更新，如果不带则为插入(需要有主键生成策略) 当有主键，但数据库中无该条数据时会更新失败。暂时无解 TODO
     *
     * @param user 用户信息
     * @return USER
     */
    @PostMapping(value = "insert")
    public User saveUser(User user) {
        return dao.save(user);
    }

    /**
     * 根据ID删除用户
     *
     * @param id key
     */
    @DeleteMapping(value = "delete/{id}")
    public void deleteUserById(@PathVariable String id) {
        dao.deleteById(Long.parseLong(id));
    }

}
