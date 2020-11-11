package com.project.spring.aop.factory;

import org.springframework.cglib.proxy.*;

import java.lang.reflect.Method;

/**
 * 使用cglib就是为了弥补动态代理的不足【动态代理的目标对象一定要实现接口】
 */
public class ProxyFactory {

    // 维护目标对象
    private static Object target;

    // 维护关键点代码的类
    private static AOP aop;

    /**
     * @param target_ 需要增强的类
     * @param aop_    增强方法抽取出来的泛用类AOP
     * @return obj
     */
    public static Object getProxyInstance(Object target_, AOP aop_) {
        // 目标对象和关键点代码的类都是通过外界传递过来
        target = target_;
        aop = aop_;

        // 新的代理实例，在被增强类调用方法时，使用aop类进行增强方法
        return Proxy.newProxyInstance(
                // 被增强类的类加载器
                target.getClass().getClassLoader(),
                // 这个对象所实现的接口
                target.getClass().getInterfaces(),
                new InvocationHandler() {
                    // 对方法进行增强
                    @Override
                    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                        aop.begin();
                        // 放射进行执行
                        Object returnValue = method.invoke(target, objects);
                        aop.close();
                        // 返回值
                        return returnValue;
                    }
                }
        );
    }
}
