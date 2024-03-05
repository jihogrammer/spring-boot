package dev.jihogrammer.member;

import dev.jihogrammer.member.model.MemberId;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public record Member(MemberId id, String name, String password, Integer age) {

    public Member {
        if (isNull(id)) {
            throw new IllegalArgumentException("member id is null");
        }
        if (isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException("member name is blank");
        }
        if (nonNull(password) && password.isBlank()) {
            throw new IllegalArgumentException("member password is blank");
        }
    }

}
