package dev.jihogrammer.domain.members.port.in;

import dev.jihogrammer.domain.members.exception.MemberException;
import dev.jihogrammer.domain.members.model.SignInCommand;
import dev.jihogrammer.domain.members.model.SignUpCommand;
import dev.jihogrammer.domain.members.port.out.InMemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SignUpUsageTest {

    static InMemoryMemberRepository members;

    static SignUpUsage signUpUsage;

    @BeforeAll
    static void setUpClass() {
        members = new InMemoryMemberRepository();
        signUpUsage = new SignUpInteractor(members);
    }

    @AfterEach
    void tearDown() {
        members.clear();
    }

    @Test
    void signUp() throws MemberException {
        // given
        var command = SignUpCommand.builder().name("hello").password("world").build();

        // when
        var member = signUpUsage.signUp(command);

        // then
        assertThat(member.username()).isEqualTo(command.username());
    }

}
