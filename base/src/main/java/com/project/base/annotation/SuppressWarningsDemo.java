package com.project.base.annotation;

/**
 * @SuppressWarnings 示例
 */
@SuppressWarnings({"unused", "deprecation"})
public class SuppressWarningsDemo {
    public void unusedVariable() {
        int unused = 10;  // 未使用的变量，但被抑制警告
        OldClass old = new OldClass();
        old.oldMethod();  // 调用过时方法，警告被抑制
    }
}
