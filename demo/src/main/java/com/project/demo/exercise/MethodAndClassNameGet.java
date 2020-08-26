package com.project.demo.exercise;

/**
 * 通过Thread.currentThread().getStackTrace()，得到相关信息。实际应用场景，在观察日志时，更加方便的定位。
 *
 * @author Reisen
 */
public class MethodAndClassNameGet {

    private static void doStaticVoid() {
        System.out.println("文件名" + Thread.currentThread().getStackTrace()[1].getFileName());
        System.out.println("类名" + Thread.currentThread().getStackTrace()[1].getClassName());
        System.out.println("方法名" + Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    private void nameTest() {
        // 获取类所在包名.类名
        System.out.println("全称" + this.getClass().getName());
        // 获取所在类名的简称
        System.out.println("类名" + this.getClass().getSimpleName());
        // Thread.currentThread().getStackTrace()[1].getMethodName() 可以获取当前方法的方法名
        System.out.println("方法名" + Thread.currentThread().getStackTrace()[1].getMethodName());
        // Thread.currentThread().getStackTrace()[1].getMethodName() 可以获得所在的行数
        System.out.println("所在行数" + Thread.currentThread().getStackTrace()[1].getLineNumber());
    }

    private void doExceptionTest() {
        new NullPointerException().getStackTrace();
        throw new NullPointerException();
    }

    public static void main(String[] args) {
        MethodAndClassNameGet get = new MethodAndClassNameGet();
        get.nameTest();
        System.out.println("调用静态方法");
        doStaticVoid();
    }
}
