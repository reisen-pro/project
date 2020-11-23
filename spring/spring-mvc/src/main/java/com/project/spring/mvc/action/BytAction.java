package com.project.spring.mvc.action;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class BytAction implements Controller {
    @Override
    public ModelAndView handleRequest(javax.servlet.http.HttpServletRequest httpServletRequest, javax.servlet.http.HttpServletResponse httpServletResponse) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        // 未配置视图解析器  modelAndView.setViewName("/bye.jsp");
        modelAndView.setViewName("bye");
        return modelAndView;
    }
}
