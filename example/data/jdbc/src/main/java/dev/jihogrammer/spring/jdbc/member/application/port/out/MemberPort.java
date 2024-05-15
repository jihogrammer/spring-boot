package dev.jihogrammer.spring.jdbc.member.application.port.out;

import dev.jihogrammer.spring.jdbc.member.domain.Member;
import dev.jihogrammer.spring.jdbc.member.domain.MemberId;
import dev.jihogrammer.spring.jdbc.member.domain.exception.MemberException;

import java.sql.Connection;

public interface MemberPort {

    Member save(Member member);

    Member findById(MemberId memberId);

    default Member findById(Connection connection, MemberId memberId) {
        throw new MemberException("Should implement.");
    }

    Member update(Member member);

    default Member update(Connection connection, Member member) {
        throw new MemberException("Should implement.");
    }

    boolean delete(MemberId memberId);

}
