package dev.jihogrammer.member.adaptor.in.web;

import dev.jihogrammer.member.adaptor.in.web.model.MemberViewModel;
import dev.jihogrammer.member.application.port.in.MemberQuery;
import dev.jihogrammer.member.application.port.in.MemberSignUpCommand;
import dev.jihogrammer.member.application.port.in.MemberSignUpUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/spring/members")
@RequiredArgsConstructor
public class SpringMemberController {

    private final MemberSignUpUseCase memberSignUpUseCase;

    private final MemberQuery memberQuery;

    @GetMapping("/sign-up")
    public String signUp() {
        return "sign-up-form";
    }

    @PostMapping("/sign-up")
    public String signUp(
        @RequestParam("name") final String name,
        @RequestParam("age") final Integer age,
        final Model model
    ) {
        final var command = MemberSignUpCommand.builder()
            .name(name)
            .email(name + "@jihogrammer.dev")
            .age(age)
            .build();
        final var member = this.memberSignUpUseCase.signUp(command);
        final var memberViewModel = MemberViewModel.of(member);

        model.addAttribute("member", memberViewModel);

        return "sign-up-result";
    }

    @GetMapping
    public String members(final Model model) {
        final var members = this.memberQuery.findAll();
        final var memberViewModels = members.stream().map(MemberViewModel::of).toList();

        model.addAttribute("members", memberViewModels);

        return "members";
    }

}
