package com.project.demo.exercise;

import java.util.function.Supplier;

/**
 * Supplier java8函数式接口实现的一种快捷方式
 *
 * @author Reisen
 * @see java.util.function.Supplier
 */
public class SupplierTest {
    public static void main(String[] args) {
        int[] arr = {9, 6, 88, 5, 13, 2, 4};
        int maxValue = getMax(() -> {
            int max = arr[0];
            for (int value : arr) {
                max = Math.max(max, value);
            }
            return max;
        });

        System.out.println(maxValue);
    }

    private static int getMax(Supplier<Integer> sup) {
        return sup.get();
    }
}
