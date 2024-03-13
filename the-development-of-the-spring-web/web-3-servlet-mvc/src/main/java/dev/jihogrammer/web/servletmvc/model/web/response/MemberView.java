package dev.jihogrammer.web.servletmvc.model.web.response;

import dev.jihogrammer.member.model.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberView {

    public Long id;

    public String name;

    public Integer age;

    public static MemberView of(final Member member) {
        return new MemberView(member.id().value(), member.name(), member.age());
    }

    public static Collection<MemberView> of(final Collection<Member> members) {
        return members.stream().map(MemberView::of).toList();
    }

}
