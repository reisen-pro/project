package com.project.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author Reisen
 * @title: MyBeanFactoryPostProcessor
 * @projectName project
 * @description: TODO
 * @date 2021/1/9 12:49
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.getenv();
        System.getProperties();

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("");
    }
}
