package com.project.base.collection;

import java.util.ArrayList;

/**
 * 集合扩容时间测试
 *
 * @author Reisen
 */
public class ListExpansion<T> {

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        final int N = 10000000;

        long startTime = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            list.add(i);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("使用ensureCapacity方法前：" + (endTime - startTime));

        list = new ArrayList<>();
        long startTime1 = System.currentTimeMillis();
        list.ensureCapacity(N);
        for (int i = 0; i < N; i++) {
            list.add(i);
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("使用ensureCapacity方法后：" + (endTime1 - startTime1));

        list = new ArrayList<>((int) (N * 0.75));
        long startTime2 = System.currentTimeMillis();
        list.ensureCapacity(N);
        for (int i = 0; i < N; i++) {
            list.add(i);
        }
        long endTime2 = System.currentTimeMillis();
        System.out.println("设置初始容量后：" + (endTime2 - startTime2));
    }
}
