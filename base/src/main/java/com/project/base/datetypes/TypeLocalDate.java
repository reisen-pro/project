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
     *  LocalDate LocalDateTime LocalTime
     */

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        LocalDate localDate1 = LocalDate.of(2020,5,23);

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.of(2020,5,23,15,9,30);

        LocalTime localTime = LocalTime.now();
        LocalTime localTime1 = LocalTime.of(15,9,48);

        System.out.println(localDate);
        System.out.println(localDateTime);
        System.out.println(localTime);

        System.out.println(localDate1);
        System.out.println(localDateTime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(localTime1);
    }
}
