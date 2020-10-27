package com.project.base.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Reisen
 * @title: PhantomReferenceDemo
 * @projectName project
 * @description: 虚引用 在真正的垃圾回收器回收之前，发现有一个虚引用，并不会真正的回收，而是放到队列中去检查。
 * 虚引用最大的作用是在于管理直接内存，堆外内存
 * @date 2020/10/27 22:48
 */
public class PhantomReferenceDemo {
    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {
        PhantomReference<M> phantomReference = new PhantomReference<>(new M(), QUEUE);
        // 直接拿不到
        System.out.println(phantomReference.get());

        new Thread(() -> {
            int i = 1;
            while (true) {
                // 集合扩容 1兆
                LIST.add(new byte[1024 * 1024]);
                try {
                    // 等待一秒
                    Thread.sleep(1000);
                    System.out.println(i);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // 打断线程
                    Thread.currentThread().interrupt();
                }
                // 打印引用对象
                System.out.println(phantomReference.get());
            }
        }).start();

        // 垃圾回收线程
        new Thread(() -> {
            while (true) {
                Reference<? extends M> poll = QUEUE.poll();
                if (poll != null) {
                    System.out.println("--- 虚引用对象被gc回收了 ---" + poll);
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
