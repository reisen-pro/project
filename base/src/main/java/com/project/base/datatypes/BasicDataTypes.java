package com.project.base.datatypes;

/**
 * 一些基础的数据类型
 *
 * @author Reisen
 */
public class BasicDataTypes {

    /**
     * 基本数据类型
     */
    private static final int anInt = 0;
    private static final long aLong = 0L;
    private static final short aShort = 0;
    private static final byte aByte = 0;
    private static final double aDouble = 0.0;
    private static final float aFloat = 0.0F;
    private static final boolean aBoolean = false;
    private static final char aChar = '0';

    /**
     * 包装后
     */
    private static final Integer anInt1 = 0;
    private static final Long aLong1 = 0L;
    private static final Short aShort1 = 0;
    private static final Byte aByte1 = 0;
    private static final Double aDouble1 = 0.0;
    private static final Float aFloat1 = 0.0F;
    private static final Boolean aBoolean1 = false;
    private static final Character aChar1 = '0';

    /**
     * 常用方法
     */
    public static void main(String[] args) {

        // 字符串转数字 转换异常时抛出 NumberFormatException
        System.out.println("字符串10000 " + Integer.parseInt("10000"));
        System.out.println("字符串10000 " + Integer.valueOf("10000"));

        // 字符串转 前字符串 后为需要转换的进制
        System.out.println("2进制数字10 " + Integer.parseInt("10", 2));
        System.out.println("8进制数字10 " + Integer.valueOf("10", 8));

        // 比较两个大小 相等返回0 前者小于后者返回-1 大于则返回1
        System.out.println("比较两个数字之间大小 " + Integer.compare(100000, 10000));

        // 得到数字位数
        System.out.println("10000的数字位数为: " + Integer.bitCount(10000));

        // 经典案例
        Integer integer1 = 100;
        Integer integer2 = 100;
        Integer integer3 = 128;
        Integer integer4 = 128;
        System.out.println("100 == 100 " + (integer1 == integer2));
        System.out.println("128 == 128 " + (integer3 == integer4));
        System.out.println("128 equals 128 " + (integer3.equals(integer4)));

        // 用于分析数字
        System.out.println("分析010 " + Integer.decode("010"));
        System.out.println("分析0x10 " + Integer.decode("0x10"));

        // 如果参数是有限浮点值，则返回true ; 返回false （对于NaN和无穷大参数）。
        System.out.println(Double.isFinite(1.00));
    }
}
