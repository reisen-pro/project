package com.project.base.reference;

/**
 * @author Reisen
 * @title: M
 * @projectName reisen
 * @description: TODO
 * @date 2020/10/5 23:15
 */
public class M {
    /**
     * 不要重写
     * @throws Throwable
     */
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }
}
