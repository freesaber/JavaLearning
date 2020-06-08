package com.bjpowernode.springboot.web;

import com.bjpowernode.springboot.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//@Controller
//相当于控制层类加上@Controller+方法上加上@ResponseBody,意味着当前控制器类中所有方法返回的都是JSON对象
@RestController
public class StudentController {

    @RequestMapping(value = "student")
    // @ResponseBody
    public Object student(){
        Student student = new Student();
        return student;
    }

    // RESTful请求风格要求路径中使用的单词都是名词，最好不要出现动词
}
