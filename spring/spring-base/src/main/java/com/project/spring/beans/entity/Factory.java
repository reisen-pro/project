package com.project.spring.beans.entity;

/**
 * 定义一个工厂类
 * @author reisen
 */
public class Factory {
    /**
     * 创建对象
     * @return user
     */
    public User getBean() {
        return new User("0","factoryCreate");
    }
}
