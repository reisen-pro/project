package com.project.util.leetcode.study;

public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[i]) {
                    swap(arr, i, j);
                }
            }
        }
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];// arr[j] = (arr[i] ^ arr[j]) ^ arr[j]
        // arr[j] = arr[i]
        arr[i] = arr[i] ^ arr[j];// arr[i] = (arr[i] ^ arr[j]) ^ arr[i]
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 5, 1, 6, 2};
        bubbleSort(arr);
        for (int i : arr) {
            System.out.print(i);
        }
    }
}
