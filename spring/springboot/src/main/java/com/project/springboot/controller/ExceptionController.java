package com.project.springboot.controller;

import com.project.springboot.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("ex")
public class ExceptionController {

    @GetMapping("ex")
    public void getEx() {
        throw new IllegalArgumentException();
    }

    @GetMapping("ex1")
    public void getEx1(){
        throw new ResourceNotFoundException();
    }
}
