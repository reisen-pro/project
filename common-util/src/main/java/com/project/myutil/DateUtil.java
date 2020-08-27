package com.project.myutil;

import java.lang.reflect.InvocationTargetException;
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
    public static String afterFormat(Class<?> c, String format) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Object o = c.getMethod("now").invoke(null);
        return (String) o.getClass().getMethod("format", DateTimeFormatter.class).invoke(o, DateTimeFormatter.ofPattern(format));
    }
}
