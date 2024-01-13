package dev.jihogrammer.item.login.web.home;

import dev.jihogrammer.item.login.web.SessionConstant;
import dev.jihogrammer.member.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import static java.util.Objects.nonNull;

@Controller
public class HomeController {
    @GetMapping("/")
    public String homePage(
        @SessionAttribute(name = SessionConstant.LOGGED_IN_MEMBER, required = false) final Member member,
        final Model model
    ) {
        if (nonNull(member)) {
            model.addAttribute("member", member);
            return "member-home";
        }

        return "home";
    }
}
