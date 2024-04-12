package dev.jihogrammer.members.adaptor.in.web.entity;

import dev.jihogrammer.member.domain.model.MemberSignInCommand;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInPayload {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public MemberSignInCommand toCommand() {
        return new MemberSignInCommand(this.username, this.password);
    }

}
