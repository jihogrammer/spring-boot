package dev.jihogrammer.servlet.mvc.service;

import dev.jihogrammer.member.port.in.MemberRegisterCommand;
import dev.jihogrammer.member.port.out.Members;
import dev.jihogrammer.servlet.mvc.model.MemberView;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class MemberService {

    private final Members members;

    public MemberView save(final String name, final Integer age) {
        var command = MemberRegisterCommand.builder().name(name).age(age).build();
        var registeredMember = this.members.register(command);
        return new MemberView(registeredMember);
    }

    public List<MemberView> findAll() {
        return this.members.findAll().stream().map(MemberView::new).toList();
    }

}
