package com.project.spring.mvc.action;

import com.project.spring.mvc.entity.User;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping(value = "/demo")
public class DemoController {

    /* 表明重写InitBinder方法 如果注册了全局的时间转换，再访问到这个单独的controller时，由于就近原则，这个会作用，全局的不会 */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
        binder.registerCustomEditor(
                Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true)
        );
    }

    @RequestMapping(value = "/hello.action")
    public String hello(Model model) {
        System.out.println("HelloAction::hello()");
        model.addAttribute("message", "hello!");
        return "/hello.jsp";
    }

    @RequestMapping(value = "/bye.action", method = RequestMethod.POST)
    public String bye(Model model) {
        model.addAttribute("message", "bye bye!");
        return "/bye.jsp";
    }

    /* 也可以直接用 @PostMapping 省去method = RequestMethod.POST*/
    @PostMapping(value = "/hello.controller")
    public String helloController(String id, String username) {
        System.out.println("id:" + id);
        System.out.println("username:" + username);
        return "/ok.jsp";
    }

    @PostMapping(value = "/user.action")
    public String helloUser(Model model, User user) {
        System.out.println(user);
        model.addAttribute("message", "hello user!\n" + user.getUsername());
        return "/index.jsp";
    }

    @GetMapping(value = "/json.action")
    @ResponseBody
    public String json() {
        return "hello json";
    }
}
