package com.project.email.restful;

import com.project.email.service.CopyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author Reisen
 */
@RestController
public class Controller {

    @Autowired
    private CopyService service;

    @GetMapping(value = "test")
    public List queryAll() {
        Date date = new Date();
        return service.CopyAToB();
    }
}
