package dev.jihogrammer.item.login.web.signin;

import dev.jihogrammer.item.login.web.session.SessionManager;
import dev.jihogrammer.item.login.web.signin.model.MemberLoginHttpRequest;
import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.port.in.MemberLoginUsage;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.NoSuchElementException;

import static java.util.Objects.requireNonNull;

@Controller
@Slf4j
public class SignInController {
    private final MemberLoginUsage memberLoginUsage;
    private final SessionManager sessionManager;

    public SignInController(final MemberLoginUsage memberLoginUsage, final SessionManager sessionManager) {
        this.memberLoginUsage = requireNonNull(memberLoginUsage);
        this.sessionManager = requireNonNull(sessionManager);
    }

    @GetMapping("/sign-in")
    public String loginView(@ModelAttribute("payload") final MemberLoginHttpRequest httpRequest) {
        return "/sign-in";
    }

    @PostMapping("/sign-in")
    public String login(
        @Valid @ModelAttribute("payload") final MemberLoginHttpRequest httpRequest,
        final BindingResult bindingResult,
        final HttpServletResponse httpServletResponse,
        final Model model
    ) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> log.error("{}", error));
            return "/sign-in";
        }

        try {
            Member member = this.memberLoginUsage.login(httpRequest.getUsername(), httpRequest.getPassword());
            this.sessionManager.createSession(member, httpServletResponse);

            model.addAttribute("member", member);

            log.info("login succeed - {}", member);

            return "redirect:/";
        } catch (NoSuchElementException e) {
            bindingResult.reject("login-fail", "check your username or password");
            return "/sign-in";
        }
    }

    @GetMapping("/logout")
    public String logout(final HttpServletRequest httpServletRequest) {
        this.sessionManager.expire(httpServletRequest);
        return "redirect:/";
    }
}
