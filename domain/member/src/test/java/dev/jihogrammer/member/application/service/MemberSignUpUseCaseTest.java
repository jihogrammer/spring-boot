package dev.jihogrammer.member.application.service;

import dev.jihogrammer.member.adaptor.out.persistence.MemberPersistenceAdaptorFactory;
import dev.jihogrammer.member.application.port.in.MemberSignUpCommand;
import dev.jihogrammer.member.application.port.in.MemberSignUpUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberSignUpUseCaseTest {

    MemberSignUpUseCase memberSignUpUseCase;

    @BeforeEach
    void setUp() {
        this.memberSignUpUseCase = new MemberSignUpService(MemberPersistenceAdaptorFactory.createMemberPort());
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
        var member = this.memberSignUpUseCase.signUp(command);

        // then
        assertThat(member.name()).isEqualTo(command.name());
        assertThat(member.email()).isEqualTo(command.email());
    }

}
