package dev.jihogrammer.member.port.in;

import dev.jihogrammer.member.adpator.in.MemberSignUpInteractor;
import dev.jihogrammer.member.adpator.out.InMemoryMemberRepository;
import dev.jihogrammer.member.model.MemberSignUpCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberSignUpUsageTest {

    static InMemoryMemberRepository members;

    static MemberSignUpUsage signUpUsage;

    @BeforeAll
    static void setUpClass() {
        members = new InMemoryMemberRepository();
        signUpUsage = new MemberSignUpInteractor(members);
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
