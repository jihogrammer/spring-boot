package dev.jihogrammer.basic.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf/template")
public class TemplateController {

    @GetMapping("/fragment")
    public String fragmentMain() {
        return "/thymeleaf/fragmentMain";
    }

    @GetMapping("/layout")
    public String layoutMain() {
        return "/thymeleaf/layoutMain";
    }

    @GetMapping("/extend-layout")
    public String extendLayout() {
        return "/thymeleaf/extendLayout";
    }

}
