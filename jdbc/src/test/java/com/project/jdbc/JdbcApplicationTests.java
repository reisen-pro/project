package com.project.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.concurrent.Executor;

@SpringBootTest
class JdbcApplicationTests {

    /**
     *  ����jdbcTemplate�������ã���������ʵ��������һ�㲻�ᵥ��ʹ��Spring jdbc��һ����������־ò��ܽ��ʹ��
     */

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {

    }
}
