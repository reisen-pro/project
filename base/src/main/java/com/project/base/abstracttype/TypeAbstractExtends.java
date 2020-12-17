package com.project.base.abstracttype;

/**
 * 继承抽象类
 * @author Reisen
 */
public class TypeAbstractExtends extends TypeAbstract {

    private TypeAbstractExtends() {
        System.out.println("子类实例化");
    }

    @Override
    public void print(){
        System.out.println("子类print");
    }

    /**
     * 子类自己的方法
     * @param str 字符串
     * @param times 次数
     */
    public void print(String str,int times){
        for (int i = 0; i < times; i++) {
            System.out.println("子类"+str);
        }
    }

    public static void main(String[] args) {
        // 子类实例化  先调用父类的构造方法 再调用子类的构造方法
        TypeAbstractExtends typeAbstractExtends = new TypeAbstractExtends();

        // 执行打印方法
        typeAbstractExtends.print();
        // 执行有参的打印方法，此时因为没有重写过 父类的String入参print方法，所以子类调用的是父类的String入参print方法
        typeAbstractExtends.print("abc");

        System.out.println("-----------------分割线------------------");

        // 父类的引用指向子类的实例
        TypeAbstract typeAbstract = new TypeAbstractExtends();

        // 调用了实例对象的打印方法
        typeAbstract.print();

        // TypeAbstract类不会有子类独有的方法
        // typeAbstract.print("hello",2);
    }
}
