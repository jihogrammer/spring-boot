package dev.jihogrammer.member.adaptor.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringHomeController {

    @GetMapping
    public String home() {
        return "redirect:/legacy/home";
    }

}
