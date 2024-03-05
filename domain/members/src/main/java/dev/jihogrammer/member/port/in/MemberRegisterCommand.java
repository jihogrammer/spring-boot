package dev.jihogrammer.member.port.in;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberRegisterCommand {

    private String name;

    private String password;

    private Integer age;

}
