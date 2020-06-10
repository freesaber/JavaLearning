package com.bjpowernode.springboot.web;

import com.bjpowernode.springboot.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    // 登录
    @RequestMapping(value = "/login")
    public @ResponseBody Object login(HttpServletRequest request){
        // 将用户的信息存放到session中
        User user = new User();
        user.setId(1001);
        user.setUserName("zhangsan");
        request.getSession().setAttribute("user", user);

        return "login success";
    }

    // 个人中心，改请求需要用户登录后才能访问
    @RequestMapping(value = "/center")
    public @ResponseBody Object center(){
        return "seee center";
    }

    // 错误页面，不登录也可以访问
    @RequestMapping(value = "/error")
    public @ResponseBody Object error(){
        return "error page";
    }
}
