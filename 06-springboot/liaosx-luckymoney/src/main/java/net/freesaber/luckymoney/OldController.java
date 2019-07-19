package net.freesaber.luckymoney;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OldController {

    @GetMapping("old")
    public String old() {
        return "index"; // 模板渲染
    }
}
