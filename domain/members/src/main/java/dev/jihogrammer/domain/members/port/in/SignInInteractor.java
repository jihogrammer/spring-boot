package dev.jihogrammer.domain.members.port.in;

import dev.jihogrammer.domain.members.exception.MemberException;
import dev.jihogrammer.domain.members.model.Member;
import dev.jihogrammer.domain.members.model.SignInCommand;
import dev.jihogrammer.domain.members.port.out.Members;

import static java.util.Objects.isNull;

public class SignInInteractor implements SignInUsage {

    private final Members members;

    public SignInInteractor(final Members members) {
        this.members = members;
    }

    @Override
    public Member signIn(final SignInCommand command) throws MemberException {
        if (isNull(command)) {
            throw new MemberException("SignInCommand is null.");
        }

        return this.members.findByUsername(command.username())
                .filter(member -> member.password().equals(command.password()))
                .orElseThrow(() -> new MemberException("Could not find the member."));
    }

}
