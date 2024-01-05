package dev.jihogrammer.member.service;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.model.in.MemberRegisterCommand;
import dev.jihogrammer.member.port.in.MemberRegisterUsage;
import dev.jihogrammer.member.port.out.Members;

import static java.util.Objects.requireNonNull;

public class MemberRegisterService implements MemberRegisterUsage {
    private final Members members;

    public MemberRegisterService(final Members members) {
        this.members = requireNonNull(members);
    }

    @Override
    public Member register(final MemberRegisterCommand command) {
        return members.save(command);
    }
}
