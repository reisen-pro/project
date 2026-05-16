package com.project.base.annotation;

/**
 * @Override 示例
 */
public class ChildClass extends ParentClass {
    @Override
    public void method() {
        System.out.println("子类重写方法");
    }

    // @Override  // 如果父类没有此方法，编译报错
    // public void nonExistMethod() {}
}
