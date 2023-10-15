package dev.jihogrammer.spring_mvc.controller;

import dev.jihogrammer.member.Members;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MemberListController {
    private static final String URI = "/members";
    private static final String TARGET_VIEW_NAME = "list";
    private static final String MEMBER_ATTRIBUTE_NAME = "members";

    private final Members members;

    public MemberListController(final Members members) {
        this.members = members;
    }

    @RequestMapping(URI)
    public ModelAndView members() {
        ModelAndView modelAndView = new ModelAndView(TARGET_VIEW_NAME);
        modelAndView.addObject(MEMBER_ATTRIBUTE_NAME, members.findAll());
        return modelAndView;
    }
}
