package dev.jihogrammer.spring.typeconverter.adaptor.in.web;

import dev.jihogrammer.spring.typeconverter.adaptor.in.web.entity.NumberAndLocalDateTimePayload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;

@Controller
public class FormatterController {

    @GetMapping("/formatter-edit")
    public String edit(final Model model) {
        model.addAttribute("form", new NumberAndLocalDateTimePayload(123456, LocalDateTime.now()));
        return "/formatter-edit";
    }

    @PostMapping("/formatter-edit")
    public String submit(@ModelAttribute("form") NumberAndLocalDateTimePayload form) {
        System.out.println("form = " + form);
        return "/formatter-view";
    }

}
