package dev.jihogrammer.domain.members.port.in;

import dev.jihogrammer.domain.members.exception.MemberException;
import dev.jihogrammer.domain.members.model.SignInCommand;
import dev.jihogrammer.domain.members.model.SignUpCommand;
import dev.jihogrammer.domain.members.port.out.InMemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SignInUsageTest {

    static InMemoryMemberRepository members;

    static SignUpUsage signUpUsage;

    static SignInUsage signInUsage;

    @BeforeAll
    static void setUpClass() {
        members = new InMemoryMemberRepository();
        signUpUsage = new SignUpInteractor(members);
        signInUsage = new SignInInteractor(members);
    }

    @AfterEach
    void tearDown() {
        members.clear();
    }

    @Test
    void signIn() throws MemberException {
        // given
        var username = "hello";
        var password = "world";
        signUpUsage.signUp(SignUpCommand.builder().name(username).password(password).build());

        // when
        var signedInMember = signInUsage.signIn(new SignInCommand(username, password));

        // then
        assertThat(signedInMember.username()).isEqualTo(username);
        assertThat(signedInMember.password()).isEqualTo(password);
    }

}
