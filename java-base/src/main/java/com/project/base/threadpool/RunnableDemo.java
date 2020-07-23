package com.project.base.threadpool;

public class RunnableDemo implements Runnable {

    private String name;

    private final Object object = new Object();

    private RunnableDemo(String name) {
        this.name = name;
    }

    private static int sum = 50;

    @Override
    synchronized public void run() {
        while (sum > 0) {
            System.out.println(name + " -- " + sum);
            synchronized (object) {
                sum--;
            }
        }
    }

    public static void main(String[] args) {
        RunnableDemo runnableDemo1 = new RunnableDemo("线程1");
        RunnableDemo runnableDemo2 = new RunnableDemo("线程2");

        new Thread(runnableDemo1).start();
        new Thread(runnableDemo2).start();
    }
}
