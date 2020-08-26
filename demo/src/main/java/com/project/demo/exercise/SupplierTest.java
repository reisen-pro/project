package com.project.demo.exercise;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * Supplier
 *
 * @author jidq_sinosoft
 * @see java.util.function.Supplier
 */
public class SupplierTest {
    public static void main(String[] args) {
        int[] arr = {9, 6, 88, 5, 13, 2, 4};
        int maxValue = getMax(() -> {
            int max = arr[0];
            for (int value : arr) {
                if (max < value) {
                    max = value;
                }
            }
            return max;
        });

        System.out.println(maxValue);
    }

    private static int getMax(Supplier<Integer> sup) {
        return sup.get();
    }
}
