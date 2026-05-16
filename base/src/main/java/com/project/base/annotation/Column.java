package com.project.base.annotation;

import java.lang.annotation.*;

/**
 * 列名注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
    String name() default "";

    int length() default 255;

    boolean nullable() default true;

    boolean unique() default false;
}
