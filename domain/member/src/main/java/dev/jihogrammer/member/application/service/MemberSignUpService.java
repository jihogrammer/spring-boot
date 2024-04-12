package dev.jihogrammer.member.application.service;

import dev.jihogrammer.member.application.port.out.MemberPort;
import dev.jihogrammer.member.domain.Member;
import dev.jihogrammer.member.domain.model.MemberSaveCommand;
import dev.jihogrammer.member.domain.model.MemberSignUpCommand;
import dev.jihogrammer.member.application.port.in.MemberSignUpUseCase;

public class MemberSignUpInteractor implements MemberSignUpUseCase {

    private final MemberPort memberPort;

    public MemberSignUpInteractor(final MemberPort memberPort) {
        this.memberPort = memberPort;
    }

    @Override
    public Member signUp(final MemberSignUpCommand command) {
        var saveCommand = MemberSaveCommand.builder()
                .role(command.role())
                .name(command.name())
                .email(command.email())
                .password(command.password())
                .age(command.age())
                .build();

        return this.memberPort.save(saveCommand);
    }

}
