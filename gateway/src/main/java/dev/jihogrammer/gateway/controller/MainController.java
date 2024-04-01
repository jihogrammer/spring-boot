package dev.jihogrammer.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final GetMappingEndPointFinder getMappingEndPointFinder;

    @RequestMapping
    public String index(final Model model) {
        model.addAttribute("title", "Gateway");
        model.addAttribute("uris", this.getMappingEndPointFinder.endPoints());

        return "/index";
    }

}
