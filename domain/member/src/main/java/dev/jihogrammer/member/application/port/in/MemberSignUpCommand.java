package dev.jihogrammer.member.domain.model;

import lombok.Builder;

@Builder
public record MemberSignUpCommand(
    MemberRole role,
    String name,
    String email,
    String password,
    Integer age
) {
}
