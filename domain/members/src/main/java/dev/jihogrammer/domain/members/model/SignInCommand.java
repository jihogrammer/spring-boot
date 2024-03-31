package dev.jihogrammer.domain.members.model;

import dev.jihogrammer.domain.members.exception.MemberValidationException;

import static java.util.Objects.isNull;

public record SignInCommand(
        String username,
        String password
) {

    public SignInCommand {
        if (isNull(username) || username.isBlank()) {
            throw new MemberValidationException("username is blank.");
        }
        if (isNull(password) || password.isBlank()) {
            throw new MemberValidationException("password is blank.");
        }
    }

}
