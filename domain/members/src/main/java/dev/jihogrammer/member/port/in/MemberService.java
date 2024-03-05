package dev.jihogrammer.member.port.in;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.model.MemberSignUpCommand;
import dev.jihogrammer.member.port.out.Members;

import java.util.NoSuchElementException;

import static java.util.Objects.isNull;

public class MemberService implements MemberSignInUsage, MemberSignUpUsage {

    private final Members members;

    public MemberService(final Members members) {
        this.members = members;
    }

    @Override
    public Member signIn(final String name, final String password) {
        if (isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException("name is blank");
        }
        if (isNull(password) || password.isBlank()) {
            throw new IllegalArgumentException("password is blank");
        }

        return members.findByName(name)
            .filter(member -> member.password().equals(password))
            .orElseThrow(() -> new NoSuchElementException("could not found the member"));
    }

    @Override
    public Member signUp(final MemberSignUpCommand command) {
        if (isNull(command)) {
            throw new IllegalArgumentException("command is null");
        }

        return this.members.save(command);
    }
}
