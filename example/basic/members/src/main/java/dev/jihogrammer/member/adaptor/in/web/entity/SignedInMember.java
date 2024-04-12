package dev.jihogrammer.members.adaptor.in.web.entity;

import dev.jihogrammer.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignedInMember {

    private String username;

    public static SignedInMember of(final Member member) {
        return new SignedInMember(member.name());
    }

}
