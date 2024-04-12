package dev.jihogrammer.member.adaptor.in.web.model;

import dev.jihogrammer.member.domain.Member;

public record MemberViewModel(
    Long id,
    String name,
    Integer age
) {

    public static MemberViewModel of(final Member member) {
        return new MemberViewModel(
            member.id().value(),
            member.name(),
            member.age());
    }

}
