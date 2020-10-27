package com.project.base.reference;

import java.util.concurrent.TimeUnit;

/**
 * @author Reisen
 * @title: ThreadLocalDemo
 * @projectName project
 * @description: TODO
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