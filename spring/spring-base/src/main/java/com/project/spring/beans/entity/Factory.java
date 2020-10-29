package com.project.spring.beans.entity;

public class Factory {
    public User getBean() {
        return new User("0","factoryCreate");
    }
}
