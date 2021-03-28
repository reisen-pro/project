package com.project.base.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Reisen
 * @title: CyclicBarrierDemo
 * @projectName project
 * @description: CyclicBarrier 直到运行完成之前，才会执行后面的程序 跟 countdownlatch相反
 * @date 2021/3/28 19:11
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new Thread(() -> {
            System.out.println("运行完毕，打印结果");
        }));
        for (int i = 1; i <= 10; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName());
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
