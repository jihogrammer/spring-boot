package dev.jihogrammer.web.servletmvc.service;

import dev.jihogrammer.member.port.in.MemberRegisterCommand;
import dev.jihogrammer.member.port.out.Members;
import dev.jihogrammer.web.servletmvc.model.MemberView;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberService {

    private final Members members;

    public MemberView save(final String name, final Integer age) {
        var command = MemberRegisterCommand.builder().name(name).age(age).build();
        var registeredMember = this.members.register(command);
        return new MemberView(registeredMember);
    }

    public Iterable<MemberView> findAll() {
        return this.members.findAll().stream().map(MemberView::new).toList();
    }

}
