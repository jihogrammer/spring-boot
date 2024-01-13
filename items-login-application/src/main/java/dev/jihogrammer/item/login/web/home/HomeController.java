package dev.jihogrammer.item.login.web.home;

import dev.jihogrammer.item.login.web.session.SessionManager;
import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.model.vo.MemberId;
import dev.jihogrammer.member.port.out.Members;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Optional;

@Controller
public class HomeController {
    private final SessionManager sessionManager;

    public HomeController(final SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @GetMapping("/")
    public String homePage(
        final HttpServletRequest httpServletRequest,
        final Model model
    ) {
        Optional<Member> loggedInMember = this.sessionManager.findMemberByHttpServletRequest(httpServletRequest);

        if (loggedInMember.isPresent()) {
            model.addAttribute("member", loggedInMember.get());
            return "member-home";
        } else {
            return "home";
        }
    }
}
