package com.project.spring.mvc.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RestController {

    @GetMapping("/item/{id}")
    public @ResponseBody String viewItems(@PathVariable("id") Integer id) throws Exception {
        return "shopping id:" + id;
    }
}
