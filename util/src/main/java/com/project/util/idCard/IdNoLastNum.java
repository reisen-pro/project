package com.project.util.idCard;

import java.util.ArrayList;

/**
 * 用于计算身份证最后一位
 * 根据计算公式，求和
 * 根据和求余数
 * 根据余数找对应的结果
 */
public class IdNoLastNum {

    // 身份证各个位置的乘数
    public final static String ArithmeticNum = "7－9－10－5－8－4－2－1－6－3－7－9－10－5－8－4－2";
    // 余数对应的数值
    public static final String RemainderConversion = "1－0－X－9－8－7－6－5－4－3－2";

    public static String lastNum(String idNo) {
        ArrayList<Integer> idNoNumList = new ArrayList();
        for (char c : idNo.substring(0, 17).toCharArray()) {
            idNoNumList.add(Integer.valueOf(String.valueOf(c)));
        }
        String[] modulusList = ArithmeticNum.split("－");
        ArrayList<Integer> modulus = new ArrayList<>();
        for (String s : modulusList) {
            modulus.add(Integer.valueOf(s));
        }
        int sum = 0;
        for (int i = 0; i < idNoNumList.size(); i++) {
            int x = idNoNumList.get(i);
            int y = modulus.get(i);
            sum = sum + (x * y);
        }
        int lastNum = sum % 11;
        String[] lastNumList = RemainderConversion.split("－");
        return lastNumList[lastNum];
    }

    public static void main(String[] args) {
        // 求身份证最后一位
        System.out.println(lastNum(""));
    }
}
