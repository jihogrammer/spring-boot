package dev.jihogrammer.spring.jdbc.member.domain;

import dev.jihogrammer.spring.jdbc.member.domain.exception.MemberException;

import java.util.UUID;

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

    public static Member of(final String id, final Integer money) {
        return new Member(new MemberId(id), money);
    }

    public static Member of(final UUID uuid, final Integer money) {
        return of(uuid.toString(), money);
    }

}
