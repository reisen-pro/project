package com.project.base.threadpool;

/**
 * 线程测试类
 * 加了两个synchronized之后，目前是比较稳定的
 */
public class ThreadDemo extends Thread {

    private static int num = 5000;

    private final Object object = new Object();

    @Override
    public void run() {
        while (num > 0) {
            // 加在while里面 如果加在while上面顺序有的时候会乱
            synchronized (object) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 这里也要加 上面的锁粒度可能不够 仅仅加上面的
                synchronized (object) {
                    System.out.println(getName() + " " + num--);
                }
            }
        }
    }

    public static void main(String[] args) {
        ThreadDemo threadDemo1 = new ThreadDemo();
        ThreadDemo threadDemo2 = new ThreadDemo();
        threadDemo1.start();
        threadDemo2.start();
    }
}
