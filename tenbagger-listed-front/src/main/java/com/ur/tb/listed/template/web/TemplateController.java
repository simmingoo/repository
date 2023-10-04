package com.ur.tb.listed.template.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TemplateController {
    @RequestMapping("/{templateNo}")
    public String getTemplate(@PathVariable("templateNo") String templateNo) {
        return "template/" + templateNo;
    }

    @RequestMapping("/test")
    public String test() {
        return "test";
    }

}
