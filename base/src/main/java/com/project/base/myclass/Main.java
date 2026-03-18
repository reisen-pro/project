package com.project.base.myclass;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 创建业务层对象
        EmployeeService service = new EmployeeService();

        // 添加员工
        service.addEmployee(new Employee(1, "Alice", 85000));
        service.addEmployee(new Employee(2, "Bob", 62000));
        service.addEmployee(new Employee(3, "Charlie", 72000));

        // 列表所有员工
        System.out.println("\n=== 所有员工 ===");
        List<Employee> all = service.listAll();
        all.forEach(System.out::println);   // ② Java 8+ 方法引用

        // 按 ID 查找
        System.out.println("\n=== 查找 ID 为 2 的员工 ===");
        Employee found = service.findById(2);
        System.out.println(found != null ? found : "未找到");

        // 删除
        service.removeById(3);
        System.out.println("\n=== 删除后列表 ===");
        service.listAll().forEach(System.out::println);
    }
}
