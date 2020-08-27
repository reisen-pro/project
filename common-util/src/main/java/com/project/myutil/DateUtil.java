package com.project.myutil;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author Reisen
 */
public class DateUtil {

    /**
     * 获取当前时间
     *
     * @return 时分秒
     * @apiNote
     */
    public static String getCurrentTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    /**
     * 获取当前时间
     *
     * @param isString 返回字符串？
     * @return 年月日
     * @apiNote
     */
    public static String getCurrentDate(boolean isString) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * 获取当前时间
     *
     * @return 年月日
     * @apiNote
     */
    public static Date getCurrentDate() {
        LocalDate localDate = LocalDate.now().plusDays(0);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 返回当前时间加上addDay以后的天数
     *
     * @param addDay 增加天数
     * @return 增加后的天数
     */
    public static Date plusDay(int addDay) {
        LocalDate localDate = LocalDate.now().plusDays(addDay);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
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
}
