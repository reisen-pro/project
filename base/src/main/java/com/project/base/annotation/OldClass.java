package com.project.base.annotation;

/**
 * @Deprecated 示例
 */
public class OldClass {
    /**
     * @deprecated 此方法已过时，请使用 newMethod()
     */
    @Deprecated
    public void oldMethod() {
        System.out.println("旧方法（已过时）");
    }

    public void newMethod() {
        System.out.println("新方法");
    }
}
