package com.project.base.annotation;

/**
 * 用户实体类 - 模拟 ORM 映射
 */
@Table(name = "t_user", schema = "mydb")
public class UserEntity {
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "username", length = 50, unique = true, nullable = false)
    private String username;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "age")
    private Integer age;

    @ApiOperation(value = "获取用户信息", method = "GET", path = "/user/{id}")
    public UserEntity getUserById(Long id) {
        return this;
    }

    @ApiOperation(value = "创建用户", method = "POST", path = "/user")
    public void createUser() {
    }

    // Getter and Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
