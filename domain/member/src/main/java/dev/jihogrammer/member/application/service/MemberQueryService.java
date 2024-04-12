package dev.jihogrammer.member.application.service;

import dev.jihogrammer.member.application.port.in.MemberQuery;
import dev.jihogrammer.member.application.port.out.MemberPort;
import dev.jihogrammer.member.domain.Member;
import dev.jihogrammer.member.domain.exception.MemberException;
import dev.jihogrammer.member.domain.model.MemberId;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@RequiredArgsConstructor
class MemberQueryService implements MemberQuery {

    private final MemberPort memberPort;

    @Override
    public Collection<Member> findAll() {
        return this.memberPort.findAll();
    }

    @Override
    public Member findById(final MemberId id) {
        return this.memberPort.findById(id)
            .orElseThrow(() -> new MemberException("Could not find member by id=[" + id + "]"));
    }

}
