package com.project.spring.mvc.action;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController implements BeanFactoryAware {
    private BeanFactory beanFactory;

    @RequestMapping(value = "/upload.action",method = RequestMethod.POST)
    public void upload(MultipartFile file) {
        beanFactory.containsBean("uploadController");
        System.out.println(file.getOriginalFilename());
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
