package dev.jihogrammer.item.login.web.signup;

import dev.jihogrammer.item.login.web.signup.model.MemberRegisterHttpRequest;
import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.model.in.MemberRegisterCommand;
import dev.jihogrammer.member.port.in.MemberRegisterUsage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import static java.util.Objects.requireNonNull;

@Controller
@Slf4j
public class SignUpController {
    private final MemberRegisterUsage memberRegisterUsage;

    public SignUpController(final MemberRegisterUsage memberRegisterUsage) {
        this.memberRegisterUsage = requireNonNull(memberRegisterUsage);
    }

    @GetMapping("/sign-up")
    public String signUpView(@ModelAttribute("member") final MemberRegisterHttpRequest httpRequest) {
        return "/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(
        @Validated @ModelAttribute("member") final MemberRegisterHttpRequest httpRequest,
        final BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "/sign-up";
        }

        MemberRegisterCommand command = MemberRegisterHttpRequest.toCommand(httpRequest);
        Member registeredMember = this.memberRegisterUsage.register(command);

        log.info("sign-up succeed: {}", registeredMember);

        return "redirect:/";
    }
}
