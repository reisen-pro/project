package com.project.base.datetypes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Reisen
 */
public class TypeDate {

    public static void main(String[] args) throws ParseException {

        // 当前时间
        Date date = new Date();
        Date sysdate = new Date(System.currentTimeMillis());

        System.out.println(date);

        // 打印本地时间 该方法已过时
        System.out.println(date.toLocaleString());
        System.out.println(sysdate.toLocaleString());

        // 日期格式化
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateFormat = format.parse("2020-01-01");
        Date dateNow = format.parse("2020-12-21");

        // 比较时间前后
        System.out.println(date.after(dateFormat));
        System.out.println(date.before(dateFormat));


        // 同一个时间比较前后
        System.out.println(date.after(sysdate));
        System.out.println(date.before(sysdate));

        String dateNowStr = format.format(date);
        date = format.parse(dateNowStr);
        System.out.println("new date compareTo date now : " + date.compareTo(dateNow));
    }
}
