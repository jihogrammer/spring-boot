package dev.jihogrammer.member.application.port.in;

import dev.jihogrammer.member.domain.model.MemberRole;
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
