package com.project.spring.aop.factory;

import org.springframework.stereotype.Component;

/**
 * 数据库事务操作 泛用方法抽取
 */
@Component
public class AOP {
    public void begin() {
        System.out.println("开始事务");
    }

    public void close() {
        System.out.println("关闭事务");
    }
}
