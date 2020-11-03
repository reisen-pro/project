package com.project.base.sync;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

/**
 * @author Reisen
 * @title: CasAndUnsafe
 * @projectName project
 * @description: TODO
 * @date 2020/11/3 23:11
 */
public class CasAndUnsafe_Sync {
    // 定义一个全局变量m
    private static int m = 0;

    public static void main(String[] args) throws InterruptedException {
        // 定义一个线程数组 大小100
        Thread[] threads = new Thread[100];
        // countDownLatch这个类使一个线程等待其他线程各自执行完毕后再执行。
        CountDownLatch latch = new CountDownLatch(threads.length);

        Object o = new Object();

        // 循环100次
        for (int i = 0; i < threads.length; i++) {
            // 一个m+10000次的线程
            threads[i] = new Thread(() -> {
                synchronized (o) {
                    for (int j = 0; j < 10000; j++) {
                        m++;
                    }
                }
                latch.countDown();
            });
        }

        // 线程启动
        Arrays.stream(threads).forEach((t) -> t.start());

        latch.await();

        System.out.println(m);
    }
}
