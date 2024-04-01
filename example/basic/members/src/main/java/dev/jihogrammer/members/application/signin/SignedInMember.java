package dev.jihogrammer.members.application.signin;

import dev.jihogrammer.domain.members.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignedInMember {

    private String username;

    public static SignedInMember of(final Member member) {
        return new SignedInMember(member.username());
    }

}
