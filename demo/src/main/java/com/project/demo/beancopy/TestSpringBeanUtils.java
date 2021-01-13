package com.project.demo.beancopy;

import org.springframework.beans.BeanUtils;

/**
 * 使用spring的BeanUtils进行对象拷贝
 */
public class TestSpringBeanUtils {
    public static void main(String[] args) {
        //下面只是用于单独测试
        PersonSource personSource = new PersonSource(1, "pjmike", "12345", 21);
        PersonDest personDest = new PersonDest();
        BeanUtils.copyProperties(personSource, personDest);
        System.out.println("persondest: " + personDest);
    }
}
