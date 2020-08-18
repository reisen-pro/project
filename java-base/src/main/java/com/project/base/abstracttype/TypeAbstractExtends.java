package com.project.base.abstracttype;

/**
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

    public void print(String printStr,int times){
        for (int i = 0; i < times; i++) {
            System.out.println("子类"+printStr);
        }
    }

    public static void main(String[] args) {

        TypeAbstractExtends typeAbstractExtends = new TypeAbstractExtends();

        typeAbstractExtends.print();
        typeAbstractExtends.print("abc");

        System.out.println("---------------------------------");

        TypeAbstract typeAbstract = new TypeAbstractExtends();
        typeAbstract.print();
        //typeAbstract.print("abc",3);
    }
}
