package dev.jihogrammer.item.login.web.member;

import dev.jihogrammer.item.login.web.member.model.MemberLoginHttpRequest;
import dev.jihogrammer.item.login.web.member.model.MemberRegisterHttpRequest;
import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.model.in.MemberRegisterCommand;
import dev.jihogrammer.member.port.in.MemberLoginUsage;
import dev.jihogrammer.member.port.in.MemberRegisterUsage;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.NoSuchElementException;

import static java.util.Objects.requireNonNull;

@Controller
@RequestMapping("/members")
@Slf4j
public class MemberController {
    private final MemberRegisterUsage memberRegisterUsage;
    private final MemberLoginUsage memberLoginUsage;

    public MemberController(final MemberRegisterUsage memberRegisterUsage, final MemberLoginUsage memberLoginUsage) {
        this.memberRegisterUsage = requireNonNull(memberRegisterUsage);
        this.memberLoginUsage = requireNonNull(memberLoginUsage);
    }

    @GetMapping("/sign-up")
    public String signUpView(@ModelAttribute("member") final MemberRegisterHttpRequest httpRequest) {
        return "/members/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUp(
        @Validated @ModelAttribute("member") final MemberRegisterHttpRequest httpRequest,
        final BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "/members/sign-up";
        }

        MemberRegisterCommand command = MemberRegisterHttpRequest.toCommand(httpRequest);
        Member registeredMember = this.memberRegisterUsage.register(command);

        log.info("sign-up succeed: {}", registeredMember);

        return "redirect:/";
    }

    @GetMapping("/sign-in")
    public String loginView(@ModelAttribute("payload") final MemberLoginHttpRequest httpRequest) {
        return "/members/sign-in";
    }

    @PostMapping("/sign-in")
    public String login(@Valid @ModelAttribute("payload") final MemberLoginHttpRequest httpRequest, final BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> log.error("{}", error));
            return "/members/sign-in";
        }

        try {
            Member member = memberLoginUsage.login(httpRequest.getUsername(), httpRequest.getPassword());
            log.info("{}", member);
            return "redirect:/";
        } catch (NoSuchElementException e) {
            bindingResult.reject("login-fail", "check your username or password");
            return "/members/sign-in";
        }
    }
}
