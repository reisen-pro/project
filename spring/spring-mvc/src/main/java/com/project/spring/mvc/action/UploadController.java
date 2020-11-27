package com.project.spring.mvc.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {
    @RequestMapping(value = "/upload.action",method = RequestMethod.POST)
    public void upload(MultipartFile file) {
        System.out.println(file.getOriginalFilename());
    }
}
