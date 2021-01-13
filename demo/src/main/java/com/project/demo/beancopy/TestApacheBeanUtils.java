package com.project.demo.beancopy;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * apache bean拷贝
 * 默认情况下，使用org.apache.commons.beanutils.BeanUtils对复杂对象的复制是引用，这是一种浅拷贝
 */
public class TestApacheBeanUtils {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        //下面只是用于单独测试
        PersonSource personSource = new PersonSource(1, "pjmike", "12345", 21);
        PersonDest personDest = new PersonDest();
        BeanUtils.copyProperties(personDest, personSource);
        System.out.println("persondest: " + personDest);
    }
}
