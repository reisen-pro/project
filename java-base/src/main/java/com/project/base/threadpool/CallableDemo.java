package com.project.base.threadpool;

import java.util.concurrent.Callable;

public class CallableDemo implements Callable {
    @Override
    public Object call() throws Exception {
        // 继承Callable Callable与Thread和Runnable的区别之一
        // Callable是一个有返回值的线程
        final String str = "deal and callback";
        return str;
    }

    public static void main(String[] args) throws Exception {
        CallableDemo callableDemo1 = new CallableDemo();
        CallableDemo callableDemo2 = new CallableDemo();
        System.out.println(callableDemo1.call());
        System.out.println(callableDemo2.call());
    }
}
