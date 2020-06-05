package net.freesaber.luckymoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("hello")
public class SayController {
    @Autowired
    private LimitConfig limitConfig;

    @GetMapping("/say")
    public String getSay(){
        return "get说明：" + limitConfig.getDescription();
    }

    @PostMapping("/say")
    public String postSay(){
        return "post说明：" + limitConfig.getDescription();
    }

    // @PathVariable获取url中的数据
    @GetMapping("/param/{id}")
    public String getPathVar(@PathVariable("id") Integer id){
        return "PathVariable id:"+id;
    }
    // @RequestParam获取请求参数值
    @GetMapping("/param")
    public String getRequestPar(@RequestParam("id") Integer id){
        return "RequestParam id:"+id;
    }

    // @RequestParam获取请求参数值非必传
    @GetMapping("paramdefault")
    public String getRequestParWithDefault(@RequestParam(value = "id",required = false,defaultValue = "0") Integer id){
        return "RequestParam id:"+id;
    }

    // @PathVariable @RequestParamy对于get和post方式没有区别。用于解析不同请求格式的数据
}
