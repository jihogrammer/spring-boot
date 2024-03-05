package dev.jihogrammer.member.port.in;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.port.out.Members;

public class MemberLoginService implements MemberLoginUsage {

    private final Members members;

    public MemberLoginService(final Members members) {
        this.members = members;
    }

    @Override
    public Member login(String name, String password) {
        return members.findByUsername(name)
            .filter(member -> member.password().equals(password))
            .orElseThrow();
    }

}
