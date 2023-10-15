package dev.jihogrammer.spring_mvc.controller;

import dev.jihogrammer.member.Members;
import dev.jihogrammer.member.model.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final Members members;

    public MemberController(final Members members) {
        this.members = members;
    }

    @RequestMapping("/new-form")
    public ModelAndView newForm() {
        return new ModelAndView("new-form");
    }

    @RequestMapping("/save")
    public ModelAndView save(final HttpServletRequest request, final HttpServletResponse response) {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);

        Member newMember = members.save(member);

        ModelAndView modelAndView = new ModelAndView("save-result");
        modelAndView.addObject("newMember", newMember);

        return modelAndView;
    }

    @RequestMapping
    public ModelAndView members() {
        ModelAndView modelAndView = new ModelAndView("list");
        modelAndView.addObject("members", members.findAll());
        return modelAndView;
    }
}
