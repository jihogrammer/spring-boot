package dev.jihogrammer.servlet.mvc.controller.spring;

import dev.jihogrammer.servlet.mvc.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("${service-uri.spring.base}")
public class MemberController {

    private final MemberService memberService;

    public MemberController(final MemberService memberRegisterUsage) {
        this.memberService = memberRegisterUsage;
    }

    @GetMapping("${service-uri.spring.form}")
    public String newForm() {
        log.info("REQUEST spring controller newForm()");
        return "new-form";
    }

    @PostMapping("${service-uri.spring.save}")
    public String save(
            @RequestParam("name") final String name,
            @RequestParam("age") final int age,
            final Model model
    ) {
        log.info("REQUEST spring controller save({}, {})", name, age);
        model.addAttribute("newMember", memberService.save(name, age));
        return "save-result";
    }

    @GetMapping
    public String members(final Model model) {
        log.info("REQUEST spring controller members({})", model);
        model.addAttribute("members", memberService.findAll());
        return "list";
    }

}
