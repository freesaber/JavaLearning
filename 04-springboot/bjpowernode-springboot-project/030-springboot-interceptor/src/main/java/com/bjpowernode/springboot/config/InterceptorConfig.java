package com.bjpowernode.springboot.config;

import com.bjpowernode.springboot.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 定义此类为配置类
public class InterceptorConfig implements WebMvcConfigurer {
    //mvc:interceptors
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 拦截user下的所有请求，必须用户登录后才能访问，但是这样拦截路径中有一些是不需要用户登录也可以访问的(login,error)
        String[] addPathPatterns = {
            "/user/**"
        };
        // 要排除的路径
        String[] excludePathPatterns ={
            "/user/login","/user/error"
        };

        //mvc:interceptor
        registry.addInterceptor(new UserInterceptor())
        .addPathPatterns(addPathPatterns)
        .excludePathPatterns(excludePathPatterns);
    }
}
