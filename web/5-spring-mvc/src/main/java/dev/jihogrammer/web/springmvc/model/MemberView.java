package dev.jihogrammer.web.springmvc.model;

import dev.jihogrammer.domain.members.model.Member;
import lombok.Getter;

@Getter
public class MemberView {

    public Long id;

    public String name;

    public Integer age;

    public MemberView(final Member member) {
        this.id = member.id().value();
        this.name = member.username();
        this.age = member.age();
    }

}
