package com.project.base.datetypes;

import java.util.Calendar;

/**
 * @author Reisen
 */
public class TypeCalendar {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        // 月份会与实际月份有出入 因为在对月份进行枚举时，是从0月开始的，故需要-1
        calendar.set(2020, Calendar.MAY,23);
        System.out.println(calendar.getTime().toLocaleString());
    }
}
