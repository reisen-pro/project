package com.project.spring.mvc.action;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * spring mvc的工作流程
 * 1.用户发送http请求，spring mvc核心控制器接受到请求
 * 2.找到映射器看该请求是否交由对应的Action类进行处理
 * 3.找到适配器有无该Action类
 * 4.Action类处理完结果封装到ModelAndView中
 * 5.通过视图解析器把数据解析，跳转到对应的jsp页面中
 */
public class HelloAction implements Controller {
    @Override
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        return modelAndView;
    }
}
