package dev.jihogrammer.member.application.service;

import dev.jihogrammer.member.application.port.out.MemberPort;
import dev.jihogrammer.member.domain.Member;
import dev.jihogrammer.member.domain.exception.MemberException;
import dev.jihogrammer.member.application.port.in.MemberSignInCommand;
import dev.jihogrammer.member.application.port.in.MemberSignInUseCase;

class MemberSignInService implements MemberSignInUseCase {

    private final MemberPort memberPort;

    public MemberSignInService(final MemberPort memberPort) {
        this.memberPort = memberPort;
    }

    @Override
    public Member signIn(final MemberSignInCommand command) {
        return this.memberPort.findByEmailAndPassword(command.email(), command.password())
                .orElseThrow(() -> new MemberException("Could not find member."));
    }

}
