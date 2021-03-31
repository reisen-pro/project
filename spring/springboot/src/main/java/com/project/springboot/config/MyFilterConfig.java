package com.project.springboot.config;

import com.project.springboot.filter.MyFilter;
import com.project.springboot.filter.MyFilterWithAnnotation;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 过滤器的配置类
 */
@SpringBootConfiguration
public class MyFilterConfig {
    // 注入自己的过滤类
    @Resource
    private MyFilter myFilter;

    // 注解的方式的过滤类
    @Resource
    private MyFilterWithAnnotation myFilterWithAnnotation;

    @Bean
    public FilterRegistrationBean<MyFilter> thirdFilter() {
        // 新建筛选器注册
        FilterRegistrationBean<MyFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        // 设置过滤器
        filterRegistrationBean.setFilter(myFilter);
        // 执行顺序
        filterRegistrationBean.setOrder(10);
        // 匹配过滤的地址 意思就是那些地址需要过滤
        filterRegistrationBean.setUrlPatterns(new ArrayList<>(Collections.singletonList("/*")));
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<MyFilterWithAnnotation> thirdFilterWithAnnotation() {
        FilterRegistrationBean<MyFilterWithAnnotation> filterRegistrationBean = new FilterRegistrationBean<>();
        // 再次新建一个过滤器
        filterRegistrationBean.setFilter(myFilterWithAnnotation);
        // 设置执行顺序
        filterRegistrationBean.setOrder(5);
        filterRegistrationBean.setUrlPatterns(new ArrayList<>(Collections.singletonList("/*")));
        return filterRegistrationBean;
    }
}
