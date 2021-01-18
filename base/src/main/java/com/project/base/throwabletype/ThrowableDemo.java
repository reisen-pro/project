package com.project.base.throwabletype;

/**
 * public string getMessage():返回异常发生时的简要描述
 * public string toString():返回异常发生时的详细信息
 * public string getLocalizedMessage():返回异常对象的本地化信息。
 * 使用Throwable的子类覆盖这个方法，可以生成本地化信息。如果子类没有覆盖该方法，则该方法返回的信息与getMessage（）返回的结果相同
 * public void printStackTrace():在控制台上打印Throwable对象封装的异常信息
 */
public class ThrowableDemo {

    static void throwNPE() {
        throw new NullPointerException("空指针异常");
    }

    public static void main(String[] args) {
        try {
            throwNPE();
        } catch (Exception e) {
            System.out.println("getMessage : " + e.getMessage());
            System.out.println("toString : " + e.toString());
            System.out.println("getLocalizedMessage : " + e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
