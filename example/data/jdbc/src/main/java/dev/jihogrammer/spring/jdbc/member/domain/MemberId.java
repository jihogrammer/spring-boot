package dev.jihogrammer.spring.jdbc.member.domain;

import dev.jihogrammer.spring.jdbc.member.domain.exception.MemberException;

public record MemberId(String value) {

    public MemberId {
        if (value == null || value.isBlank()) {
            throw new MemberException("value is blank.");
        }
    }

}
