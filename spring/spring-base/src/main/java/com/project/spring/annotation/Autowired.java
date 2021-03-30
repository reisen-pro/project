package com.project.spring.annotation;

import java.lang.annotation.*;

/**
 * @author Reisen
 * @title: Autowired
 * @projectName project
 * @description: 自定义注解上需要包含四个元注解
 * @date 2021/3/30 18:48
 */
// 表示是运行时
@Retention(RetentionPolicy.RUNTIME)
// 表示是作用在字段上
@Target(ElementType.FIELD)
// 表示可以被继承
@Inherited
// 加入doc文档
@Documented
public @interface Autowired {
}
