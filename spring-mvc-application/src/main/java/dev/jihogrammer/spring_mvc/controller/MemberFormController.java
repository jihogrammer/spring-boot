package dev.jihogrammer.spring_mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MemberFormController {
    private static final String URI = "/members/new-form";
    private static final String TARGET_VIEW_NAME = "new-form";

    @RequestMapping(URI)
    public ModelAndView newForm() {
        return new ModelAndView(TARGET_VIEW_NAME);
    }
}
