package dev.jihogrammer.item.login.web.signin.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class MemberLoginHttpRequest {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
}
