package com.project.spring.mvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 执行preHandle是顺序执行。
 * 执行postHandle、afterCompletion是倒序执行
 *
 * 如果preHandle不放行，postHandle、afterCompletion都不执行。
 * 只要有一个拦截器不放行，controller不能执行完成
 *
 * 只有前面的拦截器preHandle方法放行，下边的拦截器的preHandle才执行。
 */
public class MyHandlerInterceptor implements HandlerInterceptor {
    // 在执行handler之前来执行的
    // 用于用户认证校验、用户权限校验

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyHandlerInterceptor...preHandle");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyHandlerInterceptor...postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyHandlerInterceptor...afterCompletion");
    }
}
