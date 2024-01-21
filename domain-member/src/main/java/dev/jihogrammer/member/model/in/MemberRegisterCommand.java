package dev.jihogrammer.member.model.in;

import static dev.jihogrammer.common.utils.StringUtils.isBlank;

public record MemberRegisterCommand(String username, String password) {
    public MemberRegisterCommand {
        if (isBlank(username)) {
            throw new IllegalArgumentException("register command username is blank");
        }
        if (isBlank(password)) {
            throw new IllegalArgumentException("register command password is blank");
        }
    }
}
