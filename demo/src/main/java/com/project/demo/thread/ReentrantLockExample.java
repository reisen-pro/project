package com.project.demo.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

public class ReentrantLockExample {
    // 创建一个ReentrantLock对象
    private final Lock lock = new ReentrantLock();

    public void commonMethod() throws InterruptedException {
        // 尝试获取锁，最多等待2秒，如果2秒内无法获取锁，则放弃
        boolean isLockAcquired = lock.tryLock(2, TimeUnit.SECONDS);
        if (isLockAcquired) {
            try {
                // 执行同步代码
                System.out.println("Lock acquired by current thread: " + Thread.currentThread().getName());
                // 模拟长时间任务
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 重置中断状态
                System.out.println("The current thread was interrupted.");
            } finally {
                // 释放锁
                lock.unlock();
                System.out.println("Lock released by thread: " + Thread.currentThread().getName());
            }
        } else {
            // 如果没有获取到锁，则打印提示信息
            System.out.println("Failed to acquire the lock.");
        }
    }

    public void commonMethodWithRetry() throws InterruptedException {
        boolean isLockAcquired = false;
        int retryCount = 5; // 尝试获取锁的次数
        while (!isLockAcquired && retryCount > 0) {
            isLockAcquired = lock.tryLock(2, TimeUnit.SECONDS);
            if (!isLockAcquired) {
                System.out.println("Failed to acquire the lock, retrying...");
                retryCount--;
            }
        }
        if (isLockAcquired) {
            // 同步代码块
            try {
                System.out.println("Lock acquired by current thread: " + Thread.currentThread().getName());
                // 模拟耗时操作
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // 重置中断状态
                System.out.println("The current thread was interrupted.");
            } finally {
                lock.unlock();
                System.out.println("Lock released by thread: " + Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();
        // 创建两个线程来演示ReentrantLock的使用
        Thread thread1 = new Thread(() -> {
            try {
                example.commonMethodWithRetry();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread thread2 = new Thread(() -> {
            try {
                example.commonMethodWithRetry();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // 启动线程
        thread1.start();
        thread2.start();
    }
}