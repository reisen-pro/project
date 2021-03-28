package com.project.base.juc;

import java.util.concurrent.CountDownLatch;

/**
 * @author Reisen
 * @title: CountDownLatchDemo
 * @projectName project
 * @description: 类似于一个计数器，可以保证程序的顺序 可能会抛出一个中断异常
 * @date 2021/3/28 15:45
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 倒数计数器
        CountDownLatch countDownLatch = new CountDownLatch(10);

        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("start emission");
    }
}
