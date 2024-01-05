package dev.jihogrammer.item.login.web.member.model;

import dev.jihogrammer.member.model.in.MemberRegisterCommand;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberRegisterHttpRequest {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    public static MemberRegisterCommand toCommand(final MemberRegisterHttpRequest httpRequest) {
        return new MemberRegisterCommand(httpRequest.getUsername(), httpRequest.getPassword());
    }
}
