package com.project.base.enumtype;

/**
 * @author Reisen
 */

public enum WeekEnum {
    /**
     *  定义一周数字所代表的中文
     */
    Monday("星期一", 1),
    Tuesday("星期二", 2),
    Wednesday("星期三", 3),
    Thursday("星期四", 4),
    Friday("星期五", 5),
    Saturday("星期六", 6),
    Sunday("星期天", 7);

    private String weekDayC;
    private int weekDay;

    WeekEnum(String weekDayC, int weekDay) {
        this.weekDayC = weekDayC;
        this.weekDay = weekDay;
    }

    public static String getWeekDayC(int weekDay) {
        for (WeekEnum weekEnum : WeekEnum.values()) {
            if (weekEnum.weekDay == weekDay) {
                return weekEnum.weekDayC;
            }
        }
        return "没有该日期";
    }

    public String getWeekDayC() {
        return weekDayC;
    }

    public void setWeekDayC(String weekDayC) {
        this.weekDayC = weekDayC;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    @Override
    public String toString() {
        return "WeekEnum{" +
                "weekDayC='" + weekDayC + '\'' +
                ", weekDay=" + weekDay +
                '}';
    }

    public static void main(String[] args) {
        System.out.println(WeekEnum.getWeekDayC(1));
    }
}
