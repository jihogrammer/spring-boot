package dev.jihogrammer.member.adaptor.in.web.controller;

import dev.jihogrammer.member.adaptor.in.web.entity.FrontControllerMemberViewModel;
import dev.jihogrammer.member.adaptor.in.web.frontcontroller.ModelView;
import dev.jihogrammer.member.adaptor.in.web.model.ModelViewController;
import dev.jihogrammer.member.application.port.in.MemberSignUpCommand;
import dev.jihogrammer.member.application.port.in.MemberSignUpUseCase;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

import java.util.Map;

import static dev.jihogrammer.member.adaptor.in.web.WebEnvironment.URI_PREFIX;

@RequiredArgsConstructor
public class MemberSaveController extends ModelViewController {

    private final MemberSignUpUseCase memberSignUpUseCase;

    @Override
    public String uri() {
        return URI_PREFIX + "/members/save";
    }

    @Override
    public String view() {
        return "save-result";
    }

    @Override
    public ModelView process(final HttpServletRequest request) {
        final var name = request.getParameter("name");
        final var age = Integer.parseInt(request.getParameter("age"));
        final var command = MemberSignUpCommand.builder()
            .name(name)
            .email(name + "@jihogrammer.dev")
            .age(age)
            .build();

        final var member = this.memberSignUpUseCase.signUp(command);
        final var memberViewModel = new FrontControllerMemberViewModel(member);

        return new ModelView(this.view(), Map.of("member", memberViewModel));
    }

}
