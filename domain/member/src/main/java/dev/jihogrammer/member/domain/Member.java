package dev.jihogrammer.member;

import dev.jihogrammer.member.exception.MemberException;
import dev.jihogrammer.member.model.MemberId;
import dev.jihogrammer.member.model.MemberRole;

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
