package com.project.util.system;

import com.sun.management.OperatingSystemMXBean;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;

/**
 * @author Reisen
 */
public class SysInfoUtil {

    /**
     * 1KB
     */
    private static int kb = 1024;

    /**
     * 1M
     */
    private static int mb = kb * 1024;

    /**
     * 1GB
     */
    private static int gb = mb * 1024;

    static void printSystemInfo() {
        System.out.println("======================================");
        // 操作系统级内存情况查询
        OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        String os = System.getProperty("os.name");
        long physicalFree = osmxb.getFreePhysicalMemorySize() / mb;
        long physicalTotal = osmxb.getTotalPhysicalMemorySize() / mb;
        long physicalUse = physicalTotal - physicalFree;
        System.out.println("操作系统的版本：" + os);
        System.out.println("操作系统物理内存的空闲的空间为：" + physicalFree + " MB");
        System.out.println("操作系统物理内存的已用的空间为：" + physicalUse + " MB");
        System.out.println("操作系统总物理内存：" + physicalTotal + " MB");
    }

    static void printThreadInfo() {
        // 获得线程总数
        ThreadGroup parentThread;
        int totalThread = 0;
        for (parentThread = Thread.currentThread().getThreadGroup();
             parentThread.getParent() != null;
             parentThread = parentThread.getParent()) {
            totalThread = parentThread.activeCount();
        }
        System.out.println("获得线程总数:" + totalThread);
    }

    static void printDiskInfo() {
        //获取磁盘分区列表
        File[] roots = File.listRoots();
        int totalSpace = 0;
        int usableSpace = 0;
        double percent;
        for (File file : roots) {
            System.out.println(file.getPath() + "已经使用 = " + file.getUsableSpace() / gb + "GB");
            usableSpace += (file.getUsableSpace() / gb);
            //总空间
            System.out.println(file.getPath() + "总容量 = " + file.getTotalSpace() / gb + "GB");
            totalSpace += (file.getTotalSpace() / gb);
        }
        System.out.println("系统已使用 = " + usableSpace + "GB");
        System.out.println("系统磁盘总可用空间 = " + totalSpace + "GB");
        DecimalFormat format = new DecimalFormat("00");
        percent = usableSpace * 100.0 / totalSpace * 1.0;
        String percentStr = format.format(percent);
        System.out.println("已用磁盘空间占比：" + percentStr + "%");
    }


    /**
     * 打印jvm内存信息
     */
    static void printRunTimeMemoryInfo() {
        // 空闲内存
        long free = 0;
        // 已使用内存
        long use = 0;
        // 虚拟机已拿到内存大小
        long total = 0;
        // 虚拟机最大能拿到多少
        long max = 0;

        Runtime runtime = Runtime.getRuntime();
        total = runtime.totalMemory() / mb;
        free = runtime.freeMemory() / mb;
        max = runtime.maxMemory() / mb;
        use = total - free;
        System.out.println("JVM内存已用的空间为：" + use + " MB");
        System.out.println("JVM内存的空闲空间为：" + free + " MB");
        System.out.println("JVM总内存空间为：" + total + " MB");
        System.out.println("JVM总内存空间为：" + max + " MB");
    }


    public static void main(String[] args) {
        printSystemInfo();
        printDiskInfo();
        printRunTimeMemoryInfo();
        printThreadInfo();
    }
}
