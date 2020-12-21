package com.project.base.datetypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Reisen
 */
public class TypeLocalDate {
    /**
     * LocalDate LocalDateTime LocalTime
     */

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        // 指定年月日的localDate
        LocalDate specifyLocalDate = LocalDate.of(2020, 5, 23);

        LocalDateTime localDateTime = LocalDateTime.now();
        // 指定年月日时分秒的localDateTime
        LocalDateTime specifyLocalDateTime = LocalDateTime.of(2020, 5, 23, 15, 9, 30);

        LocalTime localTime = LocalTime.now();
        // 指定时分秒的localTime
        LocalTime specifyLocalTime = LocalTime.of(15, 9, 48);

        System.out.println("localDate : " + localDate);
        System.out.println("localTime : " + localTime);
        System.out.println("localDateTime : " + localDateTime);

        System.out.println("---------------------------------");

        System.out.println("specifyLocalDate : " + specifyLocalDate);
        System.out.println("specifyLocalTime : " + specifyLocalTime);
        System.out.println("specifyLocalDateTime : " +
                specifyLocalDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
