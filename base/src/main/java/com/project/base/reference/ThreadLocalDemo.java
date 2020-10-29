package com.project.base.reference;

import java.util.concurrent.TimeUnit;

/**
 * @author Reisen
 * @title: ThreadLocalDemo
 * @projectName project
 * @description: 可以看到ThreadLocal的源码  它的静态内部类 ThreadLocalMap threadLocals 作为容器存放
 * @date 2020/10/27 23:31
 */
public class ThreadLocalDemo {
    static ThreadLocal<Person> t1 = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 这里是null
            System.out.println(t1.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            t1.set(new Person());
        }).start();
    }
}

class Person {
    String name = "zhangsan";
}