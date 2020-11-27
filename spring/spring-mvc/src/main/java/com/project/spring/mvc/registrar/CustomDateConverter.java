package com.project.spring.mvc.registrar;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CustomDateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String s) {
        try {
            /* 进行日期转换 */
            System.out.println("CustomDateConverter::convert()");
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
