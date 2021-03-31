package com.project.springboot.validator;

import com.project.springboot.annotation.Region;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;

/**
 * 自定义的验证器
 */
public class RegionValidator implements ConstraintValidator<Region, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 匹配规则。
        HashSet<Object> regions = new HashSet<>();
        regions.add("China");
        regions.add("China-Taiwan");
        regions.add("China-HongKong");
        return regions.contains(value);
    }
}