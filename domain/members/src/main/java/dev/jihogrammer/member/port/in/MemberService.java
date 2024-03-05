package dev.jihogrammer.member.port.in;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.exception.MemberSignInException;
import dev.jihogrammer.member.port.out.Members;

public class MemberService implements MemberSignInUsage, MemberSignUpUsage {

    private final Members members;

    public MemberService(final Members members) {
        this.members = members;
    }

    @Override
    public Member signIn(final String name, final String password) {
        return members.findByName(name)
            .filter(member -> member.password().equals(password))
            .orElseThrow(MemberSignInException::new);
    }

    @Override
    public Member signUp(final MemberSignUpCommand command) {
        return this.members.save(command);
    }
}
