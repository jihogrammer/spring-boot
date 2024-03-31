package dev.jihogrammer.members.application.home;

import dev.jihogrammer.domain.members.model.Member;
import dev.jihogrammer.members.application.signin.SignIn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static java.util.Objects.isNull;

@Controller
@ConditionalOnProperty(name = "service.members.home.enabled")
@Slf4j
public class HomeController {

    public HomeController() {
        log.trace("Member Home Controller is initialized.");
    }

    @GetMapping
    public String home(@SignIn final Member member, final Model model) {
        if (isNull(member)) {
            return "/members/home";
        }

        model.addAttribute("member", member);

        return "/members/home-authed";
    }

}
