package dev.jihogrammer.member.adpator.in;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.exception.MemberException;
import dev.jihogrammer.member.model.MemberSignInCommand;
import dev.jihogrammer.member.port.in.MemberSignInUsage;
import dev.jihogrammer.member.port.out.Members;

public class MemberSignInInteractor implements MemberSignInUsage {

    private final Members members;

    public MemberSignInInteractor(final Members members) {
        this.members = members;
    }

    @Override
    public Member signIn(final MemberSignInCommand command) {
        return this.members.findByEmailAndPassword(command.email(), command.password())
                .orElseThrow(() -> new MemberException("Could not find member."));
    }

}
