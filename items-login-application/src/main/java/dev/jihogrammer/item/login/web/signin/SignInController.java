package dev.jihogrammer.item.login.web.signin;

import dev.jihogrammer.item.login.web.session.SessionHandler;
import dev.jihogrammer.item.login.web.signin.model.MemberLoginHttpRequest;
import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.port.in.MemberLoginUsage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.NoSuchElementException;
import java.util.Optional;

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
        final HttpServletRequest httpServletRequest,
        @RequestParam(defaultValue = "/") final String redirectUri,
        @Valid @ModelAttribute("payload") final MemberLoginHttpRequest memberLoginHttpRequest,
        final BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> log.error("{}", error));
            return "/sign-in";
        }

        try {
            Member member = this.memberLoginUsage.login(memberLoginHttpRequest.getUsername(), memberLoginHttpRequest.getPassword());
            SessionHandler.registerMemberSession(httpServletRequest, member);

            log.info("sign-in succeed - {}", member);

            return "redirect:" + redirectUri;
        } catch (NoSuchElementException e) {
            bindingResult.reject("login-fail", "check your username or password");
            return "/sign-in";
        }
    }

    @GetMapping("/logout")
    public String logout(final HttpServletRequest httpServletRequest) {
        Optional.ofNullable(httpServletRequest.getSession(false)).ifPresent(HttpSession::invalidate);
        return "redirect:/";
    }
}
