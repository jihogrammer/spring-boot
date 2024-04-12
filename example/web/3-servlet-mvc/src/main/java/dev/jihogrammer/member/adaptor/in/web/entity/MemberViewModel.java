package dev.jihogrammer.member.adaptor.in.web.entity;

import dev.jihogrammer.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberViewModel {

    public Long id;

    public String name;

    public Integer age;

    public static MemberViewModel of(final Member member) {
        return new MemberViewModel(member.id().value(), member.name(), member.age());
    }

    public static Collection<MemberViewModel> of(final Collection<Member> members) {
        return members.stream().map(MemberViewModel::of).toList();
    }

}
