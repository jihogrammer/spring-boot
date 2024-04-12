package dev.jihogrammer.member.application.port.out;

import dev.jihogrammer.member.domain.model.MemberRole;
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
