package com.project.base.threadpool;

/**
 * 线程测试类
 */
public class ThreadDemo extends Thread {

    private static int num = 50;

    private final Object object;

    private String name;

    private ThreadDemo(String name, Object o) {
        this.name = name;
        this.object = o;
    }

    @Override
    public void run() {
        synchronized (object) {
            do {
                System.out.println(name + "---" + num);
                num--;
            } while (num >= 1);
        }

    }

    public static void main(String[] args) {
        Object obj = new Object();
        ThreadDemo threadDemo1 = new ThreadDemo("A", obj);
        ThreadDemo threadDemo2 = new ThreadDemo("B", obj);
        threadDemo1.start();
        threadDemo2.start();
    }
}
