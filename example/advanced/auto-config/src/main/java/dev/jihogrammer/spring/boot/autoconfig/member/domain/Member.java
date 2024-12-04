package dev.jihogrammer.spring.boot.autoconfig.member.domain;

public record Member(
        MemberId id,
        String name
) {

    public Member {
        if (id == null) {
            throw new MemberException("Member id is null.");
        }
        if (name == null || name.isBlank()) {
            throw new MemberException("Member name is blank.");
        }
    }

}
