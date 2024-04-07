package dev.jihogrammer.members.application.home;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.members.application.signin.SignIn;
import dev.jihogrammer.web.core.ConditionalController;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static java.util.Objects.isNull;

@Controller
@ConditionalOnProperty(name = "service.members.home.enabled")
public class HomeController implements ConditionalController {

    @GetMapping
    public String home(@SignIn final Member member, final Model model) {
        if (isNull(member)) {
            return "/members/home";
        }

        model.addAttribute("member", member);

        return "/members/home-authed";
    }

}
