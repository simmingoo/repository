package com.ur.tb.listed.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")
public class SampleController {

    @RequestMapping("/{num}")
    public String samplePage(@PathVariable String num){
        return "sample/"+num;
    }
}
