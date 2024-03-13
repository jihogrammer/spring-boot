package dev.jihogrammer.spring.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/template")
public class TemplateController {
    @GetMapping("/fragment")
    public String fragmentMain() {
        return "/basic/fragmentMain";
    }

    @GetMapping("/layout")
    public String layoutMain() {
        return "/basic/layoutMain";
    }

    @GetMapping("/extend-layout")
    public String extendLayout() {
        return "/basic/extendLayout";
    }
}
