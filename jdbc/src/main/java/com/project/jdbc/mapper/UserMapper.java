package com.project.jdbc.mapper;


import com.project.jdbc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Reisen
 */
@Mapper
public interface UserMapper {
    /**
     * 查询所有用户列表
     *
     * @return userList
     */
    @Select("select * from user")
    public List<User> userList();

    /**
     * 根据主键查询用户
     *
     * @param id 主键
     * @return 用户
     */
    @Select("select * from user where id = #{id}")
    public User findById(Long id);

    /**
     * 根据id修改用户密码
     *
     * @param password 密码
     * @param id       用户id
     * @return
     */
    @Update("update user set password = #{password} where id = #{id}")
    public User updatePwdById(String password, Long id);
}
