package com.project.spring.mvc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 执行preHandle是顺序执行。
 * 执行postHandle、afterCompletion是倒序执行
 * <p>
 * 如果preHandle不放行，postHandle、afterCompletion都不执行。
 * 只要有一个拦截器不放行，controller不能执行完成
 * <p>
 * 只有前面的拦截器preHandle方法放行，下边的拦截器的preHandle才执行。
 */
public class MyHandlerInterceptor implements HandlerInterceptor {
    // 在执行handler之前来执行的
    // 用于用户认证校验、用户权限校验

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 在执行handler之前来执行的
        System.out.println("MyHandlerInterceptor...preHandle");

        String url = request.getRequestURI();

        // 这样的话其实也并不是很安全，因为地址栏可以伪造，这仅仅演示
        if (url.contains("login")) {
            return true;
        }

        HttpSession session = request.getSession();
        // 拿到用来校验的数据
        String userName = (String) session.getAttribute("userName");
        if (userName != null) {
            return true;
        }

        // 跳转页面
        request.getRequestDispatcher("/index.jsp").forward(request, response);
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
