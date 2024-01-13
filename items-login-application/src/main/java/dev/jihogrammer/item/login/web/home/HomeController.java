package dev.jihogrammer.item.login.web.home;

import dev.jihogrammer.item.login.session.SessionInfoLoggingService;
import dev.jihogrammer.item.login.web.SessionConstant;
import dev.jihogrammer.member.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import static java.util.Objects.nonNull;

@Controller
public class HomeController {
    private final SessionInfoLoggingService loggingService;

    public HomeController(final SessionInfoLoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @GetMapping("/")
    public String homePage(
        final HttpServletRequest request,
        @SessionAttribute(name = SessionConstant.LOGGED_IN_MEMBER, required = false) final Member member,
        final Model model
    ) {
        if (nonNull(member)) {
            this.loggingService.logSessionInfo(request.getSession(false));
            model.addAttribute("member", member);
            return "member-home";
        }

        return "home";
    }
}
