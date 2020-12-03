package com.project.spring.mvc.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class RestController {

    @GetMapping("/item/{id}")
    public @ResponseBody
    String viewItems(@PathVariable("id") Integer id) throws Exception {
        return "shopping id:" + id;
    }

    @GetMapping("/login")
    public @ResponseBody
    String login(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("userName", "Hello");
        return "login ok";
    }

    @GetMapping("/logout")
    public @ResponseBody
    String logout(HttpSession session) {
        // session失效
        session.invalidate();
        return "logout ok";
    }
}
