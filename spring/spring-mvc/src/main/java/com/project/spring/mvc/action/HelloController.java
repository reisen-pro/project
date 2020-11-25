package com.project.spring.mvc.action;

import com.project.spring.mvc.entity.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        User user = new User();
        String[] idArr = httpServletRequest.getParameterMap().get("id");
        String[] usernameArr = httpServletRequest.getParameterMap().get("username");
        if (idArr == null || usernameArr == null){return new ModelAndView("form");}
        String id = idArr[0];
        String username = usernameArr[0];
        user.setId(id);
        user.setUsername(username);
        System.out.println(httpServletRequest);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ok");
        modelAndView.addObject("USER",user);
        return modelAndView;
    }
}
