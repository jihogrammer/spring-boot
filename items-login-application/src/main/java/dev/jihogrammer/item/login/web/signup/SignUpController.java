package dev.jihogrammer.item.login.web.signup;

import dev.jihogrammer.item.login.LoginApplication;
import dev.jihogrammer.item.login.web.signup.model.MemberRegisterHttpRequest;
import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.port.in.MemberSignUpCommand;
import dev.jihogrammer.member.port.in.MemberSignUpUsage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SignUpController {

    /**
     * @see LoginApplication#memberService()
     */
    private final MemberSignUpUsage memberService;

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

        Member registeredMember = this.memberService.signUp(MemberSignUpCommand.builder()
            .name(httpRequest.getUsername())
            .password(httpRequest.getPassword())
            .build());

        log.info("sign-up succeed: {}", registeredMember);

        return "redirect:/";
    }

}
