package com.project.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author Reisen
 * @title: MyBeanFactoryPostProcessor
 * @projectName project
 * @description: 这里举一个案例比如说将自动注入为false，设置自动装配类型为byname
 * @date 2021/1/9 12:49
 */
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.getenv();
        System.getProperties();

        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("");

        if (beanFactory.containsBean("integrationGlobalProperties")){
            // Choose on of the options - either disable autowiring for bean completly,
            // or change autowiring type
            beanFactory.getBeanDefinition("integrationGlobalProperties").setAutowireCandidate(false);

            // BeanCreationException
            beanFactory.getBeanDefinition("integrationGlobalProperties").setAttribute("autowire", "byName");
        }
    }
}
