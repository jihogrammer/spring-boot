package dev.jihogrammer.members.application.signup;

import dev.jihogrammer.domain.members.model.SignUpCommand;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SignUpPayload {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    public SignUpCommand toCommand() {
        return SignUpCommand.builder()
                .name(this.username)
                .password(this.password)
                .build();
    }

}
