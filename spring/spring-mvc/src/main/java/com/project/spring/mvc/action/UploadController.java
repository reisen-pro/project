package com.project.spring.mvc.action;

import com.project.spring.mvc.entity.Items;
import com.project.spring.mvc.group.ValidGroup1;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class UploadController implements BeanFactoryAware {
    private BeanFactory beanFactory;

    @RequestMapping(value = "/upload.action", method = RequestMethod.POST)
    public void upload(MultipartFile file) {
        beanFactory.containsBean("uploadController");
        System.out.println("file.getOriginalFilename()");
    }

    // 可以在Validated注解下指定使用哪个组
    @PostMapping("/validation.action")
    public void validation(@Validated(value = {ValidGroup1.class}) Items items, BindingResult bindingResult) {
        List<ObjectError> allErrors = bindingResult.getAllErrors();
        for (ObjectError objectError : allErrors) {
            System.out.println(objectError.getDefaultMessage());
        }
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
