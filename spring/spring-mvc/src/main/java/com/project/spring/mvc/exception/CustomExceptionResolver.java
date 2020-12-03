package com.project.spring.mvc.exception;

import lombok.SneakyThrows;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * DispatcherServlet统一try/catch捕获异常调用处理器（全系统只有一个）
 * 异常处理器需要实现HandlerExceptionResolver接口
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    // 前端控制器DispatcherServlet在进行HandlerMapping、调用HandlerAdapter执行Handler过程中
    // 如果遇到异常就会执行此方法
    // handler最终要执行的Handler，它的真实身份是HandlerMethod
    // Exception ex就是接受到的异常信息

    // @SneakyThrows lombok里的注解
    @SneakyThrows
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        // 输出异常
        e.printStackTrace();

        // 统一异常处理代码
        // 针对系统自定义的CustomException异常，就可以直接从异常类中获取异常信息，将异常处理在错误页面展示
        // 异常信息
        String message = null;
        CustomException customException = null;
        if (e instanceof CustomException) {
            customException = (CustomException) e;
        } else {
            // 针对非Custom Exception异常，对这类重新构造成一个Custom Exception，异常信息为“未知错误”
            customException = new CustomException("发生了一个意外的错误");
        }

        message = customException.getMessage();

        httpServletRequest.setAttribute("message", message);

        // 转向到错误页面
        httpServletRequest.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(httpServletRequest, httpServletResponse);
        return new ModelAndView();
    }
}
