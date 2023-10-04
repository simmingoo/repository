package com.ur.tb.listed.basic.web;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BasicController implements ErrorController {

    @GetMapping("/")
    public String index() {
        return "vue/index";
    }

    @RequestMapping("/**/{path:[^.]*}")
    public ModelAndView redirect() {
        return new ModelAndView("forward:/");
    }

}
