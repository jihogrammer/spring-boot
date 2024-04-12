package dev.jihogrammer.member.application.port.in;

import dev.jihogrammer.member.domain.Member;
import dev.jihogrammer.member.domain.model.MemberId;

import java.util.Collection;

public interface MemberQuery {

    Collection<Member> findAll();

    Member findById(MemberId id);

}
