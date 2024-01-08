package dev.jihogrammer.item.login.web.signin;

import dev.jihogrammer.item.login.web.signin.model.MemberLoginHttpRequest;
import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.port.in.MemberLoginUsage;
import jakarta.servlet.http.Cookie;
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

    public SignInController(final MemberLoginUsage memberLoginUsage) {
        this.memberLoginUsage = requireNonNull(memberLoginUsage);
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
            Member member = memberLoginUsage.login(httpRequest.getUsername(), httpRequest.getPassword());
            Cookie loginSessionCookie = new Cookie("member-id", String.valueOf(member.memberId().value()));
            httpServletResponse.addCookie(loginSessionCookie);

            model.addAttribute("member", member);

            log.info("{}, {}", member, loginSessionCookie);

            return "redirect:/";
        } catch (NoSuchElementException e) {
            bindingResult.reject("login-fail", "check your username or password");
            return "/sign-in";
        }
    }

    @GetMapping("/logout")
    public String logout(final HttpServletResponse httpServletResponse) {
        Cookie loginSessionCookie = new Cookie("member-id", null);
        loginSessionCookie.setMaxAge(0);
        httpServletResponse.addCookie(loginSessionCookie);
        return "redirect:/";
    }
}
