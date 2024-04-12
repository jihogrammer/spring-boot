package dev.jihogrammer.member.application.port.in;

import dev.jihogrammer.member.domain.exception.MemberException;

import static java.util.Objects.isNull;

public record MemberSignInCommand(
    String email,
    String password
) {

    public MemberSignInCommand {
        if (isNull(email) || email.isBlank()) {
            throw new MemberException("Command email is blank.");
        }
        if (isNull(password) || password.isBlank()) {
            throw new MemberException("Command password is blank.");
        }
    }

}
