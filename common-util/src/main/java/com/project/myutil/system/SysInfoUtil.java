package com.project.myutil.system;

import com.sun.management.OperatingSystemMXBean;

import java.io.File;
import java.lang.management.ManagementFactory;

/**
 * @author Reisen
 */
public class SysInfoUtil {
    public static void main(String[] args) {
        // 虚拟机级内存情况查询
        long vmFree = 0;
        long vmUse = 0;
        long vmTotal = 0;
        long vmMax = 0;
        int byteToMb = 1024 * 1024;
        Runtime rt = Runtime.getRuntime();
        vmTotal = rt.totalMemory() / byteToMb;
        vmFree = rt.freeMemory() / byteToMb;
        vmMax = rt.maxMemory() / byteToMb;
        vmUse = vmTotal - vmFree;
        System.out.println("JVM内存已用的空间为：" + vmUse + " MB");
        System.out.println("JVM内存的空闲空间为：" + vmFree + " MB");
        System.out.println("JVM总内存空间为：" + vmTotal + " MB");
        System.out.println("JVM总内存空间为：" + vmMax + " MB");

        System.out.println("======================================");
        // 操作系统级内存情况查询
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        String os = System.getProperty("os.name");
        long physicalFree = osmxb.getFreePhysicalMemorySize() / byteToMb;
        long physicalTotal = osmxb.getTotalPhysicalMemorySize() / byteToMb;
        long physicalUse = physicalTotal - physicalFree;
        System.out.println("操作系统的版本：" + os);
        System.out.println("操作系统物理内存的空闲的空间为：" + physicalFree + " MB");
        System.out.println("操作系统物理内存的已用的空间为：" + physicalUse + " MB");
        System.out.println("操作系统总物理内存：" + physicalTotal + " MB");

        // 获得线程总数
        ThreadGroup parentThread;
        int totalThread = 0;
        for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
                .getParent() != null; parentThread = parentThread.getParent()) {
            totalThread = parentThread.activeCount();
        }
        System.out.println("获得线程总数:" + totalThread);

        //获取磁盘分区列表
        File[] roots = File.listRoots();
        int totalSpace = 0;
        int usableSpace = 0;
        double percent;
        for (File file : roots) {
            System.out.println("已经使用 = " + file.getUsableSpace() / 1024 / 1024 / 1024 + "G");
            usableSpace += (file.getUsableSpace() / 1024 / 1024 / 1024);
            //总空间
            System.out.println("总容量 = " + file.getTotalSpace() / 1024 / 1024 / 1024 + "G");
            totalSpace += (file.getTotalSpace() / 1024 / 1024 / 1024);
        }
        System.out.println(usableSpace);
        System.out.println(totalSpace);
        System.out.println("已用全部空间：" + totalSpace + "G," + (usableSpace / totalSpace) + "%");
    }
}
