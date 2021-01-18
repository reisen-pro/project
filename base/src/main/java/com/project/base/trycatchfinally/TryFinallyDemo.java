package com.project.base.trycatchfinally;

/**
 * 当try语句和finally语句中都有return语句时，
 * 在方法返回之前，finally语句的内容将被执行，
 * 并且finally语句的返回值将会覆盖原始的返回值。
 */
public class TryFinallyDemo {

    public static int getSquare(int value) {
        try {
            return value * value;
        } finally {
            if (value == 2) {
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(getSquare(2));
    }
}
