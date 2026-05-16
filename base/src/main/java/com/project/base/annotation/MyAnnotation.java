package com.project.base.annotation;

import java.lang.annotation.*;

/**
 * 自定义注解示例
 */
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
    String value() default "";

    int count() default 1;

    String[] tags() default {};
}
