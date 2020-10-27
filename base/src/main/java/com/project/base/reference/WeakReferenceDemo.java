package com.project.base.reference;

import java.lang.ref.WeakReference;

/**
 * @author Reisen
 * @title: WeakReference
 * @projectName reisen
 * @description: 弱引用，垃圾回收看到你，就给你gc了
 * @date 2020/10/5 23:25
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());
        // 这边打印对象还是在的
        System.out.println(m.get());
        // 调用gc
        System.gc();
        // 已被回收
        System.out.println(m.get());
    }
}
