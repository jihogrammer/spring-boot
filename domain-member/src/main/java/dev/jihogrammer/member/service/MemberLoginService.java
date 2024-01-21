package dev.jihogrammer.member.service;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.port.in.MemberLoginUsage;
import dev.jihogrammer.member.port.out.Members;

public class MemberLoginService implements MemberLoginUsage {
    private final Members members;

    public MemberLoginService(final Members members) {
        this.members = members;
    }

    @Override
    public Member login(final String username, final String password) {
        return members.findByUsername(username)
            .filter(member -> member.password().equals(password))
            .orElseThrow();
    }
}
