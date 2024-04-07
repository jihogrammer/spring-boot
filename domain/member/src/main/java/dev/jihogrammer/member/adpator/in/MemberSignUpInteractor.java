package dev.jihogrammer.member.adpator.in;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.model.MemberSaveCommand;
import dev.jihogrammer.member.model.MemberSignUpCommand;
import dev.jihogrammer.member.port.in.MemberSignUpUsage;
import dev.jihogrammer.member.port.out.Members;

public class MemberSignUpInteractor implements MemberSignUpUsage {

    private final Members members;

    public MemberSignUpInteractor(final Members members) {
        this.members = members;
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

        return this.members.save(saveCommand);
    }

}
