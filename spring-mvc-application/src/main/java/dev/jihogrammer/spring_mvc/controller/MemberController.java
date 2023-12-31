package dev.jihogrammer.spring_mvc.controller;

import dev.jihogrammer.member.Members;
import dev.jihogrammer.member.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final Members members;

    public MemberController(final Members members) {
        this.members = members;
    }

    @GetMapping("/new-form")
    public String newForm() {
        return "new-form";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam("username") final String username,
            @RequestParam("age") final int age,
            final Model model
    ) {
        Member member = new Member(username, age);
        Member newMember = members.save(member);
        model.addAttribute("newMember", newMember);

        return "save-result";
    }

    @GetMapping
    public String members(final Model model) {
        model.addAttribute("members", members.findAll());
        return "list";
    }
}
