package dev.jihogrammer.spring.jdbc.member.application.port.out;

import dev.jihogrammer.spring.jdbc.member.domain.Member;

public interface MemberPort {

    Member save(Member member);

}
