package dev.jihogrammer.item.login.web.signup;

import dev.jihogrammer.item.login.web.signup.model.MemberRegisterHttpRequest;
import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.port.in.MemberRegisterCommand;
import dev.jihogrammer.member.port.out.Members;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class SignUpController {
    private final Members members;

    public SignUpController(final Members members) {
        this.members = members;
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

        Member registeredMember = this.members.register(MemberRegisterCommand.builder()
            .name(httpRequest.getUsername())
            .password(httpRequest.getPassword())
            .build());

        log.info("sign-up succeed: {}", registeredMember);

        return "redirect:/";
    }
}
