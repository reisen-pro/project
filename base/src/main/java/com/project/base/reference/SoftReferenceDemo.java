package com.project.base.reference;

import java.lang.ref.SoftReference;

/**
 * @author Reisen
 * @title: SoftReference
 * @projectName reisen
 * @description: 软引用 软引用非常适合缓存使用  设置堆大小20兆 -Xmx20M
 * 经过测试 这种引用jdk8会oom jdk11中 软引用m会回收掉空间。以供b数组创建
 * @date 2020/10/5 23:25
 */
public class SoftReferenceDemo {
    public static void main(String[] args) {
        // 10m的对象
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024 * 1024 * 10]);
        // 打印对象
        System.out.println(m.get());
        // 告知回收
        System.gc();
        try {
            // 等待0.5秒
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 打印对象
        System.out.println(m.get());
        // 创建15兆的数组对象
        byte[] b = new byte[1024 * 1024 * 15];
        // 打印软引用对象
        System.out.println(m.get());
        // 指向空
        b = null;
        // 告知gc回收
        System.gc();
        Runtime.getRuntime().gc();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 发现已经找不回来了
        System.out.println(m.get());
    }
}
