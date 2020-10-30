package com.project.spring.beans.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 定义一个 bean user
 *
 * @author reisen
 */
@Data
@AllArgsConstructor
public class User {
    String id;
    String userName;

    public User() {
        System.out.println("被无参构造创建了");
    }

    public void init() {
        System.out.println("user开始初始化");
    }

    public void destory() {
        System.out.println("user被销毁");
    }
}
