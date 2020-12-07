package com.project.base.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程池使用
 *
 * @author reisen
 */
public class ExecutorsDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10000; i++) {
            executorService.execute(new RunnableDemo("线程" + i, lock));
        }
        executorService.shutdown();
    }
}
