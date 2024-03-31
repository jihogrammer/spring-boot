package dev.jihogrammer.domain.members.port.in;

import dev.jihogrammer.domain.members.exception.MemberValidationException;
import dev.jihogrammer.domain.members.model.Member;
import dev.jihogrammer.domain.members.model.SignUpCommand;
import dev.jihogrammer.domain.members.model.SignInCommand;
import dev.jihogrammer.domain.members.port.out.Members;

import static java.util.Objects.isNull;

public class MemberService implements SignInUsage, SignUpUsage {

    private final Members members;

    public MemberService(final Members members) {
        this.members = members;
    }

    @Override
    public Member signIn(final SignInCommand command) throws MemberValidationException {
        if (isNull(command)) {
            throw new MemberValidationException("MemberSignInCommand is null.");
        }
        if (isNull(command.username()) || command.username().isBlank()) {
            throw new MemberValidationException("username is blank.");
        }
        if (isNull(command.password()) || command.password().isBlank()) {
            throw new MemberValidationException("password is blank.");
        }

        return this.members.findByUsername(command.username())
                .filter(member -> member.password().equals(command.password()))
                .orElseThrow(() -> new MemberValidationException("Could not find the member."));
    }

    @Override
    public Member signUp(final SignUpCommand command) {
        if (isNull(command)) {
            throw new IllegalArgumentException("command is null");
        }

        return this.members.save(command);
    }
}
