package com.project.spring.aop.factory;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 使用cglib就是为了弥补动态代理的不足【动态代理的目标对象一定要实现接口】
 */
public class ProxyFactory implements MethodInterceptor {

    // 维护目标对象
    private static final Object target;

    private static AOP aop;

    public ProxyFactory(Object target) {
        this.target = target;
    }

    // 给目标对象创建代理对象
    public Object getProxyInstance() {
        // 1.工具类
        Enhancer en = new Enhancer();
        // 2.设置父类
        en.setSuperclass(target.getClass());
        // 3.设置回调函数
        en.setCallback(this);
        return en.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("开始事务");
        Object returnValue = method.invoke(target, objects);
        System.out.println("提交事务...");
        return returnValue;
    }
}
