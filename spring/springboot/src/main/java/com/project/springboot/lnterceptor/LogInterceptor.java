package com.project.springboot.lnterceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LogInterceptor extends HandlerInterceptorAdapter {
    /**
     * 预处理
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理器
     * @return true
     * @throws Exception possible Exceptions
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        long startTime = System.currentTimeMillis();
        log.info("\n-------- LogInterception.preHandle --- ");
        log.info("Request URL: " + request.getRequestURL());
        log.info("Start Time: " + System.currentTimeMillis());
        // 设置一个属性 开始时间
        request.setAttribute("startTime", startTime);
        log.info("请求路径:{}", request.getContextPath());
        // response.sendRedirect("index");
        return true;
    }

    /**
     * 后期处理
     *
     * @param request      请求
     * @param response     响应
     * @param handler      处理器
     * @param modelAndView 视图
     * @throws Exception 异常
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, //
                           Object handler, ModelAndView modelAndView) throws Exception {

        log.info("\n-------- LogInterception.postHandle --- ");
        log.info("Request URL: " + request.getRequestURL());
        // You can add attributes in the modelAndView
        // and use that in the view page
    }

    /**
     * 后置处理
     *
     * @param request  请求
     * @param response 响应
     * @param handler  处理器
     * @param ex       异常
     * @throws Exception 异常
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        log.info("\n-------- LogInterception.afterCompletion --- ");
        long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        log.info("Request URL: " + request.getRequestURL());
        log.info("End Time: " + endTime);
        log.info("Time Taken: " + (endTime - startTime));
    }
}
