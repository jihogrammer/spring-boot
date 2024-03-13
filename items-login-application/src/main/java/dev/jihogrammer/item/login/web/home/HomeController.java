package dev.jihogrammer.item.login.web.home;

import dev.jihogrammer.item.login.session.SessionInfoLoggingService;
import dev.jihogrammer.item.login.web.session.SignIn;
import dev.jihogrammer.member.model.Member;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static java.util.Objects.nonNull;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final SessionInfoLoggingService loggingService;

    @GetMapping("/")
    @SuppressWarnings("unused")
    public String homePage(final HttpServletRequest request, @SignIn final Member member, final Model model) {
        if (nonNull(member)) {
            this.loggingService.logSessionInfo(request.getSession(false));
            model.addAttribute("member", member);
            return "member-home";
        }

        return "home";
    }

}
