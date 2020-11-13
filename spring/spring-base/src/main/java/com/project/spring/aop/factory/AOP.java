package com.project.spring.aop.factory;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
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

    @Pointcut("execution(* com.project.spring.aop..*(..))")
    public void pointCut_(){};

    @Before("pointCut_()")
    public void begin() {
        System.out.println("开始事务");
    }

    @After("pointCut_()")
    public void close() {
        System.out.println("关闭事务");
    }

    // 返回后通知：在调用目标方法结束后执行【出现异常不执行】
    @AfterReturning("pointCut_()")
    public void afterReturning() {
        System.out.println("afterReturning");
    }

    // 异常通知：当目标方法执行异常时执行此关注点代码
    @AfterThrowing("pointCut_()")
    public void afterThrowing() {
        System.out.println("afterThrowing");
    }

    @Around("pointCut_()")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("环绕前");
        point.proceed();
        System.out.println("环绕后");
    }
}
