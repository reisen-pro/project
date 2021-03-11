package com.project.base.threadpool;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Reisen
 * @title: MyExecutor
 * @projectName project
 * @description:
 * @date 2021/3/5 7:56
 */
public class MyExecutor {
    private LinkedBlockingDeque<Runnable> blockingDeque = null;
    private List<WorkThread> workThreads = null;
    private boolean isRun = true;

    public MyExecutor(int maxThreadCount, int dequeSize) {
        blockingDeque = new LinkedBlockingDeque(dequeSize);
        workThreads = new ArrayList<>(maxThreadCount);
        for (int i = 0; i < maxThreadCount; i++) {
            new WorkThread().start();
        }

    }

    class WorkThread extends Thread {
        @Override
        public void run() {
            while (isRun) {
                Runnable runnable = blockingDeque.poll();
                if (runnable != null) {
                    runnable.run();
                }
            }
        }
    }

    public boolean execute(Runnable runnable) {
        return blockingDeque.offer(runnable);
    }

    public void destory() {
        isRun = false;
    }

    public static void main(String[] args) {
        MyExecutor myExecutor = new MyExecutor(2, 2);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            myExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + finalI);
                }
            });
        }
    }
}
