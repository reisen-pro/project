package com.project.springboot.config;

import com.project.springboot.filter.MyFilter;
import com.project.springboot.filter.MyFilterWithAnnotation;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;

@SpringBootConfiguration
public class MyFilterConfig {
    @Resource
    MyFilter myFilter;

    @Resource
    MyFilterWithAnnotation myFilterWithAnnotation;

    @Bean
    public FilterRegistrationBean<MyFilter> thirdFilter() {
        FilterRegistrationBean<MyFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(myFilter);
        // 执行顺序
        filterRegistrationBean.setOrder(10);
        filterRegistrationBean.setUrlPatterns(new ArrayList<>(Collections.singletonList("/*")));
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<MyFilterWithAnnotation> thirdFilterWithAnnotation() {
        FilterRegistrationBean<MyFilterWithAnnotation> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(myFilterWithAnnotation);
        // 执行顺序
        filterRegistrationBean.setOrder(5);
        filterRegistrationBean.setUrlPatterns(new ArrayList<>(Collections.singletonList("/*")));
        return filterRegistrationBean;
    }
}
