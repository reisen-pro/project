package com.project.jdbc.dao;

import com.project.jdbc.entity.User;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao_jpa extends JpaRepository<User, Long> {
    /**
     * 根据id修改密码
     * 要加@Param注解入参 org.springframework.data.repository.query下
     * 不可以单加Query DML语句需要加上@Modify注解 事务虽然不是必要，但是如果装了阿里的代码规范插件，是要配置回滚的
     * @param password pwd
     * @param id       id
     */
    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("update User set password =:password where id =:id")
    public void updatePwdById(@Param("password") String password, @Param("id") Long id);
}
