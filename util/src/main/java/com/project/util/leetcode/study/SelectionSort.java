package com.project.util.leetcode.study;

public class SelectionSort {
    public static void selectSort(int[] arr) {
        // i为比较数的下标，从0开始
        for (int i = 0; i < arr.length - 1; i++) {
            // minIndex 用于记录最小的一个数的下标 假设第一个数就是最小值
            int minIndex = i;
            // 被比较的数为比较数的下一个值，也就是i+1
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            // 交换
            swap(arr, i, minIndex);
        }
    }

    // 交换
    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 1, 6, 2, 6, 4, 8, 1, 5};
        selectSort(arr);
        for (int i : arr) {
            System.out.print(i);
        }
    }
}
