package com.project.demo.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * 借助 openjdk的jol工具 可以查看java对象内存布局
 * 包括 1.object header mark word
 * 对象指针 2.class pointer
 * 从JDK 1.6开始，64位的JVM支持UseCompressedOops选项 -XX:+UseCompressedOops 开启压缩指针 -XX:-UseCompressedOops  关闭指针压缩
 * 3.实例数据
 * 4.padding padding 是为了将不足的内存填充成cpu读取一组数据的量。是用来提高效率的
 *
 * @author Reisen
 */
public class ObjectLayout {

    private final String str = "str";
    private final Long l = 123L;

    public static void main(String[] args) {
        // 实例一个新的对象
        Object o = new Object();
        // 打印对象布局信息
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        ObjectLayout objectLayout = new ObjectLayout();
        System.out.println(ClassLayout.parseInstance(objectLayout).toPrintable());

        System.out.println("======================================================");
        // 调用hashcode方法以后，对象头中才会存储hashcode
        o.hashCode();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        // 锁住对象头
        synchronized (o) {
            System.out.println("========================================");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
