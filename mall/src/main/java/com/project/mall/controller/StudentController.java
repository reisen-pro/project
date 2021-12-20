package com.project.mall.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/student")
    public String getStudentData(@RequestHeader("Host") String host) {
        System.out.println(host);
        return "[{id : ' 001' ,name : 'tom\" ,age:18},{id : '002 ' ,name : \"jerry \" ,age:19},{id: '003' ,name : 'tony ' ,age:120}]";
    }
}
