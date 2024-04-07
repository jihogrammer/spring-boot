package dev.jihogrammer.member.model;

import lombok.Builder;

@Builder
public record MemberSaveCommand(
    MemberRole role,
    String name,
    String email,
    String password,
    Integer age
) {
}
