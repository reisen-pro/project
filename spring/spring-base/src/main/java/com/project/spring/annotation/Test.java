package com.project.spring.annotation;

import java.util.stream.Stream;

/**
 * @author Reisen
 * @title: Test
 * @projectName project
 * @description: 简单的实现一个自定义注解进行装配的过程
 * @date 2021/3/30 18:51
 */
public class Test {
    public static void main(String[] args) {
        TestController testController = new TestController();
        Class<? extends TestController> clazz = testController.getClass();

        // 获取所有属性 使用getfields，只能获取到public的 使用declaredfields可以获取所有属性
        Stream.of(clazz.getDeclaredFields()).forEach(field -> {
            // 判断属性是否有注解
            Autowired annotation = field.getAnnotation(Autowired.class);
            if (annotation != null) {
                field.setAccessible(true);
                Class<?> type = field.getType();
                // 实例化对象
                try {
                    Object o = type.newInstance();
                    field.set(testController,o);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println(testController.getTestService());
    }
}
