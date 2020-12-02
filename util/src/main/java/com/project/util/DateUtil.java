package com.project.util;

import org.apache.cxf.bus.spring.SpringBus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Date;

/**
 * @author Reisen
 */
public class DateUtil {

    /**
     * 获取当前时间
     *
     * @return Date 无时分秒
     */
    public static Date getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        return localDateToDate(localDate);
    }

    /**
     * 返回格式化以后的字符串
     *
     * @return String
     */
    public static String getCurrentDateTimeStr(String format) {
        String str = null;
        try {
            str = afterFormat(LocalDateTime.class, format);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return str;
    }

    /**
     * 返回日期的字符串
     *
     * @return String
     */
    public static String getCurrentDateStr() {
        return getCurrentDateTimeStr("yyyy-MM-dd");
    }

    /**
     * 返回HH:mm:ss格式的时间
     *
     * @return String
     */
    public static String getCurrentTimeStr() {
        return getCurrentDateTimeStr("HH:mm:ss");
    }

    /**
     * 返回年-月-日 时：分：秒格式的字符串
     *
     * @return String
     */
    public static String getCurrentDateTimeStr() {
        return getCurrentDateTimeStr("yyyy-MM-dd HH:mm:ss");
    }


    /**
     * 返回当天加上一定天数以后的日期
     *
     * @param plus 增加天数
     * @return 增加后的天数
     */
    public static Date plusDays(int plus) {
        LocalDate localDate = LocalDate.now().plusDays(plus);
        return localDateToDate(localDate);
    }

    /**
     * 返回当天减去一定天数以后的日期
     *
     * @param minus 减去天数
     * @return 减去后的天数
     */
    public static Date minusDays(int minus) {
        LocalDate localDate = LocalDate.now().minusDays(minus);
        return localDateToDate(localDate);
    }

    /**
     * 根据日期和时间进行转换 例如 new Date,"10:10:10" 返回 今天10点10分10秒
     *
     * @param date date
     * @param time 时分秒
     * @return localDateTime
     */
    public static LocalDateTime formatLocalDateTime(Date date, String time) {
        LocalDate localDate = dateToLocalDate(date);
        LocalTime localTime = LocalTime.parse(time);
        return LocalDateTime.of(localDate, localTime);
    }

    /**
     * LocalDate 转 Date
     *
     * @param localDate param
     * @return Date
     */
    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * Date 转 LocalDate
     *
     * @param date date
     * @return localDate
     */
    public static LocalDate dateToLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    /**
     * 返回格式化以后的字符串。
     *
     * @param temporal 时间类型
     * @param format   格式化
     * @return String
     */
    public static String afterFormat(Temporal temporal, String format) {
        if (temporal instanceof LocalDate) {
            return LocalDate.now().format(DateTimeFormatter.ofPattern(format));
        }
        if (temporal instanceof LocalTime) {
            return LocalTime.now().format(DateTimeFormatter.ofPattern(format));
        }
        if (temporal instanceof LocalDateTime) {
            return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
        }
        return null;
    }

    /**
     * 使用反射，返回格式化后的日期字符串
     *
     * @param c      class
     * @param format format
     * @return String
     */
    public static String afterFormat(Class<? extends Temporal> c, String format) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = c.getMethod("now");
        method.setAccessible(true);
        method.isAccessible();
        Object o = method.invoke(null);
        return (String) o.getClass().getMethod("format", DateTimeFormatter.class).invoke(o, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 测试性能 反射创建对象
     * 上述代码在调用 Object o = now() 时耗时较多，当时就考虑，创建一个静态全局变量 static Object obj
     * if(obj == null) {
     * //obj反射调用now();
     * }
     * 一测试确实，节省了很多时间，但是问题来了，如果这样处理，那么调用的多次afterformat方法，循环测试调用在多次，都是同一个时间，导致数据不准确。
     * 实为舍本逐末，放弃这条。
     * 如果真的有这种场景，我目前的思路是使用防抖的思想去提高性能，减少耗时。
     *
     * @param args args
     * @throws NoSuchMethodException     exception
     * @throws IllegalAccessException    exception
     * @throws InvocationTargetException exception
     */
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int testCount = 1000000;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < testCount; i++) {
            afterFormat(LocalDateTime.class, "yyyy-MM-dd");
        }
        System.out.println("反射调用耗时:" + (System.currentTimeMillis() - startTime));

        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < testCount; i++) {
            afterFormat(LocalDate.now(), "yyyy-MM-dd");
        }
        System.out.println("匹配类型调用耗时:" + (System.currentTimeMillis() - startTime2));
    }
}
