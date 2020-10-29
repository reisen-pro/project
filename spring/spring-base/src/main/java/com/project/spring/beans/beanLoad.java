package com.project.spring.beans;

import com.project.spring.beans.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


/**
 * @author reisen
 * spring 中总体看来可以通过三种方式来配置对象
 * 1.使用xml文件配置
 * 2.使用注解来配置
 * 3.使用javaConfig来配置
 */
@Slf4j
public class beanLoad {

    /**
     * 创建beanFactory对象
     *
     * @param resourceName 资源文件名
     * @return beanFactory
     */
    public static BeanFactory getFactoryByResource(String resourceName) {
        // 加载spring的资源文件
        Resource resource = new ClassPathResource(resourceName);
        // 创建ioc容器对象【ioc容器 = 工厂类+application-bean.xml】
        return new XmlBeanFactory(resource);
    }

    /**
     * 创建spring上下文对象
     *
     * @param resourceName 资源文件名
     * @return applicationContext
     */
    public static ApplicationContext getApplicationContextByResource(String resourceName) {
        // 得到ioc容器
        return new ClassPathXmlApplicationContext(resourceName);
    }


    public static void main(String[] args) {
        String resourceName = "application-bean.xml";
        // 创建bean工厂
        BeanFactory beanFactory = getFactoryByResource(resourceName);
        ApplicationContext ac = getApplicationContextByResource(resourceName);

        User user = (User) beanFactory.getBean("user");
        System.out.println(user);

        // 从上下文中得到对象
        User userAss = (User) ac.getBean("userAss");
        System.out.println(userAss);

        // 引用对象构造后的对象
        User userAssRef = (User) ac.getBean("userAssRef");
        System.out.println(userAssRef);

        // 通过工厂的方法创建的对象
        User userStaticFactory = (User) ac.getBean("userStaticFactory");
        System.out.println(userStaticFactory);
    }
}
