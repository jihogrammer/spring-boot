package dev.jihogrammer.front_controller.service;

import dev.jihogrammer.front_controller.model.MemberView;
import dev.jihogrammer.member.port.in.MemberRegisterCommand;
import dev.jihogrammer.member.port.out.Members;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class MemberSignUpService {

    private final Members members;

    private final String memberModelKey;

    public Map<String, Object> register(final String name, final int age) {
        var command = MemberRegisterCommand.builder().name(name).age(age).build();
        var member = this.members.register(command);
        return Map.of(this.memberModelKey, new MemberView(member));
    }

}
