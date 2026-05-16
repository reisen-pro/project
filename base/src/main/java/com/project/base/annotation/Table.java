package com.project.base.annotation;

import java.lang.annotation.*;

/**
 * 表名注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {
    String name() default "";

    String schema() default "";
}
