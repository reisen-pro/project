package com.project.base.reference;


import java.io.IOException;

/**
 * @author Reisen
 * @title: referenceDemo
 * @projectName reisen
 * @description: 强引用
 * @date 2020/10/5 23:14
 */
public class NormalReference {
    public static void main(String[] args) throws IOException {
        // 强引用
        M m = new M();
        // 指向空
        m = null;
        // 告知回收垃圾
        System.gc(); // disableExplicitGc
        // 打印此时的m
        System.out.println(m);
        // 阻塞main线程，给垃圾回收线程时间执行
        System.in.read();
    }
}
