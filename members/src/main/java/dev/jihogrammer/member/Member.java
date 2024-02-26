package dev.jihogrammer.member;

import dev.jihogrammer.member.model.MemberId;

import static dev.jihogrammer.common.utils.StringUtils.isBlank;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public record Member(MemberId id, String name, String password, Integer age) {
    public Member {
        if (isNull(id)) {
            throw new IllegalArgumentException("member id is null");
        }
        if (isBlank(name)) {
            throw new IllegalArgumentException("member name is blank");
        }
        if (nonNull(password) && isBlank(password)) {
            throw new IllegalArgumentException("member password is blank");
        }
    }
}
