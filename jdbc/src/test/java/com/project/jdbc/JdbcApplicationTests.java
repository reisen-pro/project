package com.project.jdbc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.concurrent.Executor;

@SpringBootTest
class JdbcApplicationTests {

    /**
     *  由于jdbcTemplate过于难用，放弃，且实际运用中一般不会单单使用Spring jdbc。一般会结合其他持久层框架结合使用
     */

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {

    }
}
