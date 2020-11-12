package com.project.spring.aop.factory;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 切面类
 * 数据库事务操作 泛用方法抽取
 * 这里需要去了解一下表达式
 * execution(): 表达式主体。
 * 第一个*号：表示返回类型，*号表示所有的类型。
 * 包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，com.project.spring.aop包、子孙包下所有类的方法。
 * 第二个*号：表示类名，*号表示所有的类。
 * *(…):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数。
 */
@Component
@Aspect
public class AOP {
    @Before("execution(* com.project.spring.aop..*(..))")
    public void begin() {
        System.out.println("开始事务");
    }

    @After("execution(* com.project.spring.aop..*(..))")
    public void close() {
        System.out.println("关闭事务");
    }
}
