package com.project.base.datetypes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Reisen
 */
public class TypeDate {

    public static void main(String[] args) throws ParseException {

        //当前时间
        Date date = new Date();
        Date date1 = new Date(System.currentTimeMillis());

        System.out.println(date);

        //打印本地时间

        System.out.println(date.toLocaleString());
        System.out.println(date1.toLocaleString());

        //日期格式化

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = format.parse("2020-01-01");

        Date dateNow = format.parse("2020-05-23");

        // 比较时间前后
        System.out.println(date.after(date2));
        System.out.println(date.before(date2));


        // 同一个时间比较前后
        System.out.println(date.after(date1));
        System.out.println(date.before(date1));


        // TODO 新建出来的Date 和 format之后的Date 比较是否是同一天
    }
}
