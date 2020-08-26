package com.project.base.abstracttype;

/**
 * @author Reisen
 */
public abstract class TypeAbstract {

    TypeAbstract() {
        System.out.println("父类实例化");
    }

    public void print() {
        System.out.println("父类print");
    }

    void print(String str) {
        System.out.println("父类print" + str);
    }
}
