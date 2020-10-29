package com.project.base.reference;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.nio.ByteBuffer;

/**
 * @author Reisen
 * @title: TestDirectByteBuffer
 * @projectName project
 * @description: TODO
 * @date 2020/10/27 23:15
 */
public class TestDirectByteBuffer {
    // jvm内部的内存
    ByteBuffer bufferIn = ByteBuffer.allocate(1024);
    // jvm缓冲区直接分配到外面的内存
    ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

    public static void main(String[] args) {
        // 获取 Java 线程管理 MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的 monitor 和 synchronizer 信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程 ID 和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
    }
}
