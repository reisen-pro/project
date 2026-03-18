package com.project.base.myclass;


import java.util.ArrayList;// ① 具体实现类
import java.util.Collections; // ② 工具类
import java.util.List;// ③ 接口（向上兼容）

/**
 * 负责存储、增删查等操作（使用集合）
 */
public class EmployeeService {

    // 存储数据
    // 泛型（后面会出现）可避免“强制类型转换”错误。
    // final 修饰 employees 让它不可再指向别的列表（但列表内容可变）。
    private final List<Employee> employeeList = new ArrayList<>();

    // 添加员工 入参是员工对象 对象是class实例
    public void addEmployee(Employee employee) {
        if (employee == null) {
            // 非法参数异常
            throw new IllegalArgumentException("Employee cannot be null");
        }
        employeeList.add(employee);
    }

    // 列出所有员工
    public List<Employee> listAll() {
        // 返回一个新的列表，避免外部直接修改内部数据
        return new ArrayList<>(employeeList);
    }

    // 根据员工id寻找对应的员工
    public Employee findById(int i) {
        for (Employee employee : employeeList) {
            if (employee.getId() == i){
                return employee;
            }
        }
        return null;
    }

    // 根据员工编号删除员工
    public void removeById(int i) {
        employeeList.removeIf(employee -> employee.getId() == i);
    }

    // ---------- 排序 ----------
    public List<Employee> listBySalaryDesc() {
        List<Employee> copy = listAll();
        // Java 8 的 Comparator
        copy.sort((e1,e2)-> Double.compare(e2.getSalary() ,e1.getSalary()));
        return copy;
    }
}
