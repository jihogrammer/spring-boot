package dev.jihogrammer.web.frontcontroller.service;

import dev.jihogrammer.web.frontcontroller.model.MemberView;
import dev.jihogrammer.domain.members.model.SignUpCommand;
import dev.jihogrammer.domain.members.port.out.Members;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class MemberSignUpService {

    private final Members members;

    private final String memberModelKey;

    public Map<String, Object> register(final String name, final int age) {
        var command = SignUpCommand.builder().name(name).age(age).build();
        var member = this.members.save(command);
        return Map.of(this.memberModelKey, new MemberView(member));
    }

}
