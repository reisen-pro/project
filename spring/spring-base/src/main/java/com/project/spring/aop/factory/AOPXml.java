package com.project.spring.aop.factory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 通过xml的形式来配置
 */
public class AOPXml {

    public void pointCut_(){};

    public void begin() {
        System.out.println("开始事务");
    }

    public void close() {
        System.out.println("关闭事务");
    }

    public void afterReturning() {
        System.out.println("afterReturning");
    }

    public void afterThrowing() {
        System.out.println("afterThrowing");
    }

    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("环绕前");
        point.proceed();
        System.out.println("环绕后");
    }
}
