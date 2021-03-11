package com.project.base.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程池使用
 * 使用jdk原生线程池创建，四种方式
 * 底层都是ThreadPoolExecutor实现的
 * @author reisen
 */
public class ExecutorsDemo {
    public static void main(String[] args) {
        new LinkedBlockingDeque<>();

        Lock lock = new ReentrantLock();
        // 可缓存的
        ExecutorService executorService = Executors.newCachedThreadPool();
        // 定长的线程数
        executorService = Executors.newFixedThreadPool(10);
        // 一个单线程的线程池
        executorService = Executors.newSingleThreadExecutor();
        // 定时效果的线程池
        executorService = Executors.newScheduledThreadPool(100);
        for (int i = 0; i < 10000; i++) {
            executorService.execute(new RunnableDemo("线程" + i, lock));
        }
        executorService.shutdown();
    }

}
