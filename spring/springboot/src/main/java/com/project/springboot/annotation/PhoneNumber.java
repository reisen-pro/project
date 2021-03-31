package com.project.springboot.annotation;

import com.project.springboot.validator.PhoneNumberValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * 自定义一个电话号码的校验注解
 * 具体校验内容
 * @see PhoneNumberValidator
 */
@Documented
// 约束
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
    String message() default "Invalid phone number";
    Class[] groups() default {};
    Class[] payload() default {};
}
