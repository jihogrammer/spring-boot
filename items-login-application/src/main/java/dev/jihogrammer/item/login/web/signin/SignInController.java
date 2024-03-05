package dev.jihogrammer.item.login.web.signin;

import dev.jihogrammer.item.login.LoginApplication;
import dev.jihogrammer.item.login.web.session.SessionHandler;
import dev.jihogrammer.item.login.web.signin.model.MemberLoginHttpRequest;
import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.port.in.MemberSignInUsage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.NoSuchElementException;
import java.util.Optional;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SignInController {

    /**
     * @see LoginApplication#memberService()
     */
    private final MemberSignInUsage memberService;

    @GetMapping("/sign-in")
    @SuppressWarnings("unused")
    public String loginView(@ModelAttribute("payload") final MemberLoginHttpRequest httpRequest) {
        return "/sign-in";
    }

    @PostMapping("/sign-in")
    public String login(
        final HttpServletRequest httpServletRequest,
        @RequestParam(name = "redirectUri", defaultValue = "/") final String redirectUri,
        @Valid @ModelAttribute(name = "payload") final MemberLoginHttpRequest memberLoginHttpRequest,
        final BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> log.error("{}", error));
            return "/sign-in";
        }

        try {
            Member member = this.memberService.signIn(memberLoginHttpRequest.getUsername(), memberLoginHttpRequest.getPassword());
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
