package com.project.spring.mvc.registrar;

import org.springframework.core.convert.converter.Converter;

/**
 * 字符串转换器
 */

public class StringTrimConverter implements Converter<String, String> {


    @Override
    public String convert(String s) {
        System.out.println("StringTrimConverter::convert()");
        try {
            s = s.trim();
            if ("".equals(s)) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }
}
