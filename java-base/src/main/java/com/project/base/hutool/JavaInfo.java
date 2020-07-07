package com.project.base.hutool;


public class JavaInfo {
    public static void main(String[] args) {
        cn.hutool.system.JavaInfo javaInfo = new cn.hutool.system.JavaInfo();
        System.out.println(javaInfo.getVersion());
        System.out.println(javaInfo.getVendor());
        System.out.println(javaInfo.getVendorURL());
        System.out.println(javaInfo.getVersionFloat());
        System.out.println(javaInfo.getVersionInt());
    }
}
