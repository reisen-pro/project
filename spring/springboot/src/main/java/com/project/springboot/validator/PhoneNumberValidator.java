package com.project.springboot.validator;

import com.project.springboot.annotation.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 实现约束验证器
 * @see ConstraintValidator
 * 手机号码验证器
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext constraintValidatorContext) {
        // 重写验证方法
        if (phoneField == null) {
            // can be null
            return true;
        }
        // 电话号码的匹配规则，及长度验证
        return phoneField.matches("^1(3[0-9]|4[57]|5[0-35-9]|8[0-9]|70)\\d{8}$")
                && phoneField.length() > 8 && phoneField.length() < 14;

    }
}
