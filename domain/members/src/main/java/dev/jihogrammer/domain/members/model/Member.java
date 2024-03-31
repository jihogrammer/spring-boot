package dev.jihogrammer.domain.members.model;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public record Member(
        MemberId id,
        String username,
        String password,
        Integer age
) {

    public Member {
        if (isNull(id)) {
            throw new IllegalArgumentException("Member id is null.");
        }
        if (isNull(username) || username.isBlank()) {
            throw new IllegalArgumentException("Username is blank.");
        }
        if (nonNull(password) && password.isBlank()) {
            throw new IllegalArgumentException("Password is blank.");
        }
    }

}
