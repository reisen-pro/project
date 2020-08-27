package com.project.myutil;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.Temporal;
import java.util.Date;

/**
 * @author Reisen
 */
public class DateUtil {

    /**
     * get now time
     *
     * @return time String
     * @apiNote 返回当前时间 时分秒格式字符串
     */
    public static String getCurrentTime(String format) {
        return afterFormat(LocalDateTime.now(), format);
    }

    /**
     *
     */
    public static String getCurrentTime() {
        return getCurrentTime("HH:mm:ss");
    }


    /**
     * 获取当前时间
     *
     * @param format 根据日期格式返回字符串
     * @return 年月日
     * @apiNote
     */
    public static String getCurrentDate(String format) {
        return afterFormat(LocalDate.now(), format);
    }

    /**
     * 获取当前时间
     *
     * @return Date 无时分秒
     */
    public static Date getCurrentDate() {
        LocalDate localDate = LocalDate.now();
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 返回当天加上一定天数以后的日期
     *
     * @param addDays 增加天数
     * @return 增加后的天数
     */
    public static Date plusDays(int plus) {
        LocalDate localDate = LocalDate.now().plusDays(plus);
        return LocalDateToDate(localDate);
    }

    /**
     * 返回当天减去一定天数以后的日期
     *
     * @param minusDay 减去天数
     * @return 减去后的天数
     */
    public static Date minusDays(int minus) {
        LocalDate localDate = LocalDate.now().minusDays(minus);
        return LocalDateToDate(localDate);
    }

    /**
     * 加上指定分钟后的时间戳
     *
     * @param addMinutes 需要加的分钟数
     * @return 时间戳
     */
    public static long afterPlus(LocalDateTime localDateTime, int addMinutes) {
        localDateTime = localDateTime.plusMinutes(addMinutes);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        long l = instant.toEpochMilli();
        return l;
    }

    /**
     * 根据日期和时间进行转换 例如 new Date,"10:10:10" 返回 今天10点10分10秒
     *
     * @param date date
     * @param time 时分秒
     * @return localDateTime
     */
    public static LocalDateTime formatLocalDateTime(Date date, String time) {
        LocalDate localDate = Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalTime localTime = LocalTime.parse(time);
        return LocalDateTime.of(localDate, localTime);
    }

    public static Date LocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 根据类型匹配，返回格式化以后的字符串，此写法不够优雅。暂时水平有限，希望如果有人能看到，看看有没有更好的写法
     *
     * @param temporal 时间类型
     * @param format   格式化
     * @return String
     */
    public static String afterFormat(Temporal temporal, String format) {
        String afterFormat = null;
        if (temporal instanceof LocalDate) {
            afterFormat = LocalDate.now().format(DateTimeFormatter.ofPattern(format));
        }
        if (temporal instanceof LocalTime) {
            afterFormat = LocalTime.now().format(DateTimeFormatter.ofPattern(format));
        }
        if (temporal instanceof LocalDateTime) {
            afterFormat = LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
        }
        return afterFormat;
    }

}
