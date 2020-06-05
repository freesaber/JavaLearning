package com.bjpowernode.springboot.web;

import com.bjpowernode.springboot.config.school;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @Autowired
    private school school;

    @RequestMapping("/say")
    public @ResponseBody String say(){
        return "Hello schoolName:"+school.getName()+",schoolWebsite:"+school.getWebsite();
    }
}
