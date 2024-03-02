package dev.jihogrammer.servlet.mvc.controller.spring;

import dev.jihogrammer.servlet.mvc.controller.legacy.LegacyHomeController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String home() {
        return "redirect:" + LegacyHomeController.URI;
    }

}
