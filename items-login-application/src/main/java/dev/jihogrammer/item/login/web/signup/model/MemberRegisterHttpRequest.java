package dev.jihogrammer.item.login.web.signup.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberRegisterHttpRequest {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

}
