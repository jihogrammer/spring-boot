package dev.jihogrammer.members.application.signup;

import dev.jihogrammer.member.model.MemberSignUpCommand;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignUpPayload {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    public MemberSignUpCommand toCommand() {
        return MemberSignUpCommand.builder()
                .name(this.username)
                .password(this.password)
                .build();
    }

}
