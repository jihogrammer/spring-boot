package dev.jihogrammer.item.login.web.home;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.model.vo.MemberId;
import dev.jihogrammer.member.port.out.Members;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

import static java.util.Objects.isNull;

@Controller
public class HomeController {
    private final Members members;

    public HomeController(final Members members) {
        this.members = members;
    }

    @GetMapping("/")
    public String homePage(
        @CookieValue(name = "member-id", required = false) final Long memberId,
        final Model model
    ) {
        if (isNull(memberId)) {
            return "home";
        }

        Optional<Member> loggedInMember = members.findById(new MemberId(memberId));
        if (loggedInMember.isEmpty()) {
            return "home";
        }

        model.addAttribute("member", loggedInMember.get());
        return "member-home";
    }
}
