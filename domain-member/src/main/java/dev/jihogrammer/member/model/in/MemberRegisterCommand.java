package dev.jihogrammer.member.model.in;

import lombok.Getter;

@Getter
public class MemberRegisterCommand {
    private String username;
    private String password;
}
