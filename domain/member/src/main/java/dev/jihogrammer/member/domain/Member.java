package dev.jihogrammer.member.domain;

import dev.jihogrammer.member.domain.exception.MemberException;
import dev.jihogrammer.member.domain.model.MemberId;
import dev.jihogrammer.member.domain.model.MemberRole;

import static java.util.Objects.isNull;

public record Member(
    MemberId id,
    MemberRole role,
    String name,
    String email,
    String password,
    Integer age
) {

    public Member {
        if (isNull(id)) {
            throw new MemberException("Member id is null.");
        }
        if (isNull(name) || name.isBlank()) {
            throw new MemberException("Member name is null.");
        }
        if (isNull(email) || email.isBlank()) {
            throw new MemberException("Member email is null.");
        }
    }

}
