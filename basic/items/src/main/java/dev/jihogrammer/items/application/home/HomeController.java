package dev.jihogrammer.items.application.home;

import dev.jihogrammer.web.core.ConditionalController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@ConditionalOnProperty(name = "service.items.home.enabled")
public class HomeController implements ConditionalController {

    @GetMapping
    public String home() {
        return "/items/home";
    }

}
