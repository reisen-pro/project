package com.project.base.threadpool;

/**
 * @author reisen
 */
public class RunnableDemo implements Runnable {

    private static int sum = 100000;

    private final Object object;

    private String name;

    RunnableDemo(String name, Object o) {
        this.name = name;
        this.object = o;
    }

    /**
     * 重写run方法 每次减少1
     */
    @Override
    public void run() {
        while (true) {
            synchronized (object) {
                if (sum <= 0) {
                    break;
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(name + " " + sum);
                sum--;
            }
        }
    }

    public static void main(String[] args) {
        Object obj = new Object();
        RunnableDemo runnableDemo1 = new RunnableDemo("A", obj);
        RunnableDemo runnableDemo2 = new RunnableDemo("B", obj);
        new Thread(runnableDemo1).start();
        new Thread(runnableDemo2).start();
    }
}
