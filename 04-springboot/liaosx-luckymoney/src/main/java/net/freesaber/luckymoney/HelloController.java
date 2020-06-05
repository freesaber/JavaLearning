package net.freesaber.luckymoney;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController //Spring4之后新加的注解，原来返回json需要@ResponseBody配合@Controller
//@Controller + @ResponseBody = @RestController
//@ResponseBody可以只放在方法上，使得一个Controller既可以返回模板，又可以返回json
public class HelloController {
    @GetMapping("/hello")
    // @RequestMapping(value="/hello",method = RequestMethod.GET) // Spring1.x 版本写法
    public String say() {
        return "跟慕课网廖师兄学SpringBoot!";
    }

    // 获取配置文件中参数
    @Value("${limit.minMoney}")
    private BigDecimal minMoney;

    @Value("${limit.description}")
    private String description;

    @GetMapping("/minmoney")
    public String minMoney() {
        return "minMoney:" + minMoney + " 说明：" + description;
    }

    // 通过配置对象获取参数
    @Autowired
    private LimitConfig limitConfig;

    @GetMapping("/limit")
    public String limit() {
        return "说明：" + limitConfig.getDescription();
    }

}
