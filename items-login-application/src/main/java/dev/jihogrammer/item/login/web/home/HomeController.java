package dev.jihogrammer.item.login.web.home;

import dev.jihogrammer.item.login.web.SessionConstant;
import dev.jihogrammer.member.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Controller
public class HomeController {
    @GetMapping("/")
    public String homePage(
        final HttpServletRequest httpServletRequest,
        final Model model
    ) {
        HttpSession httpSession = httpServletRequest.getSession(false);

        if (isNull(httpSession)) {
            return "home";
        }

        Object member = httpSession.getAttribute(SessionConstant.LOGGED_IN_MEMBER);
        if (nonNull(member) && Member.class.isAssignableFrom(member.getClass())) {
            model.addAttribute("member", member);
            return "member-home";
        }

        return "home";
    }
}
