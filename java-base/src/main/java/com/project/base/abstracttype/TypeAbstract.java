package com.project.base.abstracttype;

/**
 * @author Reisen
 */
public abstract class TypeAbstract {

    TypeAbstract() {
        System.out.println("父类实例化");
    }

    public void print() {
        System.out.println("父类打印" + "\"" + "\"");
    }

    void print(String printStr) {
        System.out.println("父类打印" + printStr);
    }
}
