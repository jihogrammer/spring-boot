package dev.jihogrammer.web.frontcontroller.model;

import dev.jihogrammer.member.Member;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
public class MemberView {

    private final Long id;

    private final String name;

    private final int age;

    public MemberView(final Member member) {
        this.id = member.id().value();
        this.name = member.name();
        this.age = member.age();
    }

}
