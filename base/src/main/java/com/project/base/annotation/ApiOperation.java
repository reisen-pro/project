package com.project.base.annotation;

import java.lang.annotation.*;

/**
 * API 操作注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiOperation {
    String value();

    String method() default "GET";

    String path() default "";
}
