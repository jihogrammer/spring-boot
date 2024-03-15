package dev.jihogrammer.spring.basic;

import dev.jihogrammer.spring.basic.http.controller.HttpController;
import dev.jihogrammer.spring.basic.thymeleaf.controller.TemplateController;
import dev.jihogrammer.spring.basic.thymeleaf.controller.ThymeleafController;
import dev.jihogrammer.web.ControllerUriParser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class HomeController {

    @RequestMapping
    public String index(final Model model) {
        model.addAttribute("title", BasicApplication.APP_NAME);

        var uris = new ArrayList<String>();
        uris.addAll(ControllerUriParser.parseGetUris(HttpController.class));
        uris.addAll(ControllerUriParser.parseGetUris(ThymeleafController.class));
        uris.addAll(ControllerUriParser.parseGetUris(TemplateController.class));
        model.addAttribute("uris", uris);

        return "/index";
    }

}
