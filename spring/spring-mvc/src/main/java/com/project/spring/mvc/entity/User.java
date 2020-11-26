package com.project.spring.mvc.entity;

import java.util.Date;

public class User {
    private String id;
    private String username;

    /* 如果传入一个时间参数 yyyy-MM-dd 会参数解析错误 */
    private Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", date=" + date +
                '}';
    }

    public User(String id, String username) {
        this.id = id;
        this.username = username;
    }

    public User(String id, String username, Date date) {
        this.id = id;
        this.username = username;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
