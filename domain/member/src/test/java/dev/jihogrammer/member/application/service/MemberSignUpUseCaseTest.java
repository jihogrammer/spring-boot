package dev.jihogrammer.member.port.in;

import dev.jihogrammer.member.application.service.MemberSignUpService;
import dev.jihogrammer.member.adaptor.out.persistence.InMemoryMemberAdaptor;
import dev.jihogrammer.member.application.port.in.MemberSignUpUseCase;
import dev.jihogrammer.member.application.port.in.MemberSignUpCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberSignUpUseCaseTest {

    static InMemoryMemberAdaptor members;

    static MemberSignUpUseCase signUpUsage;

    @BeforeAll
    static void setUpClass() {
        members = new InMemoryMemberAdaptor();
        signUpUsage = new MemberSignUpService(members);
    }

    @AfterEach
    void tearDown() {
        members.clear();
    }

    @Test
    void signUp() {
        // given
        var command = MemberSignUpCommand.builder()
                .name("jihogrammer")
                .email("hello")
                .password("world")
                .build();

        // when
        var member = signUpUsage.signUp(command);

        // then
        assertThat(member.name()).isEqualTo(command.name());
        assertThat(member.email()).isEqualTo(command.email());
    }

}
