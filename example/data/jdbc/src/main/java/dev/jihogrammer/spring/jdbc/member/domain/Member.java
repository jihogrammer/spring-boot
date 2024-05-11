package dev.jihogrammer.spring.jdbc.member.domain;

import dev.jihogrammer.spring.jdbc.member.domain.exception.MemberException;

public record Member(
    MemberId id,
    Integer money
) {

    public Member {
        if (id == null) {
            throw new MemberException("Member id is null.");
        }
        if (money == null) {
            throw new MemberException("Member money is null.");
        }
        if (money < 0) {
            throw new MemberException("Member money should be positive.");
        }
    }

}
