package com.bjpowernode.springboot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @RequestMapping("put")
    public Object put(String key,String value){
        return "值已成功放入";
    }
}
