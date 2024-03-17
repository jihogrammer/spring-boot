package dev.jihogrammer.gateway.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final Collection<String> basicModuleUris;

    @RequestMapping
    public String index(final Model model) {
        model.addAttribute("title", "Gateway");
        model.addAttribute("uris", basicModuleUris);

        return "/index";
    }

}
