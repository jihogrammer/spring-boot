package dev.jihogrammer.domain.members.port.in;

import dev.jihogrammer.domain.members.exception.MemberException;
import dev.jihogrammer.domain.members.model.Member;
import dev.jihogrammer.domain.members.model.SignUpCommand;
import dev.jihogrammer.domain.members.port.out.Members;

import static java.util.Objects.isNull;

public class SignUpInteractor implements SignUpUsage {

    private final Members members;

    public SignUpInteractor(final Members members) {
        this.members = members;
    }

    @Override
    public Member signUp(final SignUpCommand command) throws MemberException {
        if (isNull(command)) {
            throw new MemberException("command is null");
        }

        return this.members.save(command);
    }

}
