package dev.jihogrammer.spring.jdbc.member.application.port.out;

import dev.jihogrammer.spring.jdbc.member.domain.Member;
import dev.jihogrammer.spring.jdbc.member.domain.MemberId;

public interface MemberPort {

    Member save(Member member);

    Member findById(MemberId memberId);

}
