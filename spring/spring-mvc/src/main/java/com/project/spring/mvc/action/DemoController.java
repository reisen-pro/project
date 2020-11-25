package com.project.spring.mvc.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {
    @RequestMapping(value = "/hello.action")
    public String hello(Model model) {
        System.out.println("HelloAction::hello()");
        model.addAttribute("message", "hello!");
        return "/index.jsp";
    }

    @RequestMapping(value = "/bye.action")
    public String bye(Model model) {
        model.addAttribute("message", "bye bye!");
        return "/index.jsp";
    }
}
