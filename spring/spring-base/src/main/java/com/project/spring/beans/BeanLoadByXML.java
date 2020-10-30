package com.project.spring.beans;

import com.project.spring.beans.entity.User;
import com.project.spring.beans.service.UserService;
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
 * <p>
 * id是唯一的，配置文件中不允许出现两个id相同的<bean>
 * 在配置文件中允许出现两个name相同的<bean>，在用getBean()返回实例时，后面一个Bean被返回。
 * 如果没有id，name，则用类的全名作为name，如<bean class="com.project.Bean">,可以使用getBean("com.project.Bean")返回该实例。
 * <p>
 * 如果存在多个id和name都没有指定，且实例类都一样的
 * 第一个bean通过getBean("com.project.Bean")
 * 第二个bean通过getBean("com.project.Bean#1")
 * 第三个bean通过getBean("com.project.Bean#2") 以此类推
 *
 * 如果配置基本类的时候，注解和配置文件都使用的时候，注解和配置文件中 name 不相同的时候， 则两个不冲突，都能够生效。
 * 如果配置基本类的时候，注解和配置文件都使用的时候，注解和配置文件中 name 相同的时候， 则两个冲突，配置文件生效。
 *
 * 如果类作为引用类的时候，并且自动注入的时候，注解和配置文件都配置的时候，如果 name 相同的话，配置文件生效
 * 如果类作为引用类的时候，并且自动注入的时候，注解和配置文件都配置的时候，如果 name 不相同的话，就按照  Autowired的匹配规则去匹配。
 * 备注： Autowired的匹配规则 1.ByType 2. ByName
 */
@Slf4j
public class BeanLoadByXML {

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
        User userAss = (User) ac.getBean("userConstruction");
        System.out.println(userAss);

        // 引用对象构造后的对象
        User userAssRef = (User) ac.getBean("userRef");
        System.out.println(userAssRef);

        // 通过工厂的方法创建的对象
        User userStaticFactory = (User) ac.getBean("userStaticFactory");
        System.out.println(userStaticFactory);

        // 通过c命名空间创建的对象
        User userC = (User) ac.getBean("userC");
        System.out.println(userC);

        // 用户集合
        System.out.println("-----------------------------------------------");
        UserService userService = (UserService) ac.getBean("userService");
        userService.getUserList().forEach(System.out::println);
    }
}
