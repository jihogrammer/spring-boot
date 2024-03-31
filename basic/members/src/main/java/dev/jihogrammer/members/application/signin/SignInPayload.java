package dev.jihogrammer.members.application.signin;

import dev.jihogrammer.domain.members.model.SignInCommand;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInPayload {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public SignInCommand toCommand() {
        return new SignInCommand(this.username, this.password);
    }

}
