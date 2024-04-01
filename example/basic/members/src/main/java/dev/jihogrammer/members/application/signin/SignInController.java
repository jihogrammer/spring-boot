package dev.jihogrammer.members.application.signin;

import dev.jihogrammer.domain.members.exception.MemberException;
import dev.jihogrammer.domain.members.model.Member;
import dev.jihogrammer.domain.members.port.in.SignInUsage;
import dev.jihogrammer.web.session.port.in.Session;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class SignInController {

    private static final String SIGN_IN_MODEL_ATTR_NAME = "signInPayload";

    private final SignInUsage signInUsage;

    private final Session<Member> session;

    @GetMapping("/sign-in")
    public String signIn(@ModelAttribute(SIGN_IN_MODEL_ATTR_NAME) final SignInPayload signInPayload) {
        return "/members/sign-in";
    }

    @PostMapping("/sign-in")
    public String signIn(
            @RequestParam(name = "redirectURI", defaultValue = "/") final String redirectURI,
            @Valid @ModelAttribute(SIGN_IN_MODEL_ATTR_NAME) final SignInPayload signInPayload,
            final BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> log.error("{}", error));
            return "/members/sign-in";
        }

        try {
            var member = this.signInUsage.signIn(signInPayload.toCommand());
            this.session.flush(member);
            return "redirect:" + redirectURI;
        } catch (final MemberException e) {
            log.error("Failed to sign-in.", e);
            bindingResult.reject("sign-in-fail", "Check your username or password.");
            return "/members/sign-in";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        this.session.invalidate();
        return "redirect:/";
    }

}
