package dev.jihogrammer.thymeleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fragment")
public class TemplateController {
    @GetMapping("/main")
    public String fragmentMain() {
        return "/basic/fragmentMain";
    }
}
