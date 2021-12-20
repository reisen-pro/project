package com.project.mall.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {
    @GetMapping("/car")
    public String getStudentData(@RequestHeader("Host") String host) {
        System.out.println(host);
        return "[{id : ' 001 ' ,name: '奔驰\" ,price:199},{id : ' 002 ' ,name:'马自达\" ,price: 109},{id: ' 003 ' ,name:\"捷达\" ,price:120}]";
    }
}
