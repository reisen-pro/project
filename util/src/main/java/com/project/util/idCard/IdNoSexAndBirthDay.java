package com.project.util.idCard;

/**
 * 根据身份证得到性别和生日
 */
public class IdNoSexAndBirthDay {
    public static final String MALE = "男";
    public static final String FEMALE = "女";
    // 得到性别
    public static String getSex(String idNo) {
        if (idNo == null || idNo.isEmpty()) {return null;}
        int sexNum = Integer.parseInt(idNo.substring(16, 17));
        return sexNum % 2 == 0 ? FEMALE : MALE;
    }

    public static String getBirthday(String idNo) {
        if (idNo == null || idNo.isEmpty()) {return null;}
        String year = idNo.substring(6, 10);
        String month = idNo.substring(10, 12);
        if (month.startsWith("0")) {
            month = month.substring(1, 2);
        }
        String day = idNo.substring(12, 14);
        if (day.startsWith("0")) {
            day = day.substring(1, 2);
        }
        return year + "年" + month + "月" + day + "日";
    }

    public static void main(String[] args) {
        System.out.println(getSex(""));
        System.out.println(getBirthday(""));
    }
}
