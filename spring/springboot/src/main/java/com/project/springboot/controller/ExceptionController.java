package com.project.springboot.controller;

import com.project.springboot.exception.ResourceNotFoundException;
import com.project.springboot.exception.ResourceNotFoundExceptionByAnnotation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


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

    @GetMapping("ex2")
    public void getEx2() {
        throw new ResourceNotFoundExceptionByAnnotation("Sorry, the resource not found!");
    }

    @GetMapping("/ex3")
    public void getEx3() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sorry, the resource not found!", new ResourceNotFoundException());
    }
}