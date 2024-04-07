package dev.jihogrammer.members.application.signup;

import dev.jihogrammer.member.exception.MemberException;
import dev.jihogrammer.member.port.in.MemberSignUpUsage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
@Slf4j
public class SignUpController {

    private static final String SIGN_UP_MODEL_ATTR_NAME = "signUpPayload";

    private final MemberSignUpUsage signUpUsage;

    @GetMapping("/sign-up")
    public String signUp(@ModelAttribute(SIGN_UP_MODEL_ATTR_NAME) final SignUpPayload signUpPayload) {
        return "/members/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(
            @Valid @ModelAttribute(SIGN_UP_MODEL_ATTR_NAME) final SignUpPayload signUpPayload,
            final BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "/members/sign-up";
        }

        try {
            this.signUpUsage.signUp(signUpPayload.toCommand());
            return "redirect:/members/sign-in";
        } catch (final MemberException e) {
            log.error("Failed to sign up.", e);
            bindingResult.reject("sign-up-fail", "Check your username or password.");
            return "/members/sign-up";
        }
    }

}
