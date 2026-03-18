package com.project.base.myclass;

/**
 * 定义员工对象（属性、构造器、方法）
 *
 * 访问修饰符：private → 只能在类内部使用；public → 任何地方都能访问。
 */
public class Employee {


    // 访问修饰符：private -> 只能在本类访问

    // 原始类型（int, double, boolean 等）不需要引用 new，存储在栈里。
    private int id;            // 原始类型（primitive）

    // 引用类型（String, Employee 等）需要 new 或字面量（字符串自动包装）。
    private String name;       // 引用类型（object）
    private double salary;     // 原始类型


    // getter / setter ---------- 访问器 / 修改器 ----------
    public int getId() {
        return id;
    }

    // this 用于区分成员变量与同名参数。
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    // 构造方法 构造器 必须与类名相同，且没有返回值。
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }


    // ---------- 业务方法 ----------
    // 覆盖 toString，方便打印

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    // 如果你需要在 Set 或 Map 中使用 Employee，建议重写 equals() 和 hashCode()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;                     // 同一对象
        if (o == null || getClass() != o.getClass()) return false; // 不是同类
        Employee that = (Employee) o;
        return id == that.id;                           // 只比较业务主键（id）
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
