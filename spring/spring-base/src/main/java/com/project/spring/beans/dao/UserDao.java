package com.project.spring.beans.dao;

import org.springframework.stereotype.Repository;

/**
 * 定义一个dao
 *
 * @author reisen
 */
@Repository
public class UserDao {
    public void save() {
        System.out.println("用户数据入库");
    }
}
