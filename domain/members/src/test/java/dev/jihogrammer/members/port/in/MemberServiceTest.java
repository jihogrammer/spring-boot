package dev.jihogrammer.members.port.in;

import dev.jihogrammer.domain.members.intrastructure.adaptor.out.SingletonInMemoryMemberRepository;
import dev.jihogrammer.domain.members.model.SignInCommand;
import dev.jihogrammer.domain.members.model.SignUpCommand;
import dev.jihogrammer.domain.members.port.in.MemberService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    void setUp() {
        var members = SingletonInMemoryMemberRepository.getInstance();
        ((SingletonInMemoryMemberRepository) members).clear();
        this.memberService = new MemberService(members);
    }

    @Test
    void signUp() {
        // given
        var command = SignUpCommand.builder().name("jihogrammer").build();

        // when
        var member = this.memberService.signUp(command);

        // then
        assertThat(member.username()).isEqualTo(command.username());
    }

    @Test
    void signIn() {
        // given
        var username = "hello";
        var password = "world";
        this.memberService.signUp(SignUpCommand.builder().name(username).password(password).build());

        // when
        var signedInMember = this.memberService.signIn(new SignInCommand(username, password));

        // then
        assertThat(signedInMember.username()).isEqualTo(username);
        assertThat(signedInMember.password()).isEqualTo(password);
    }

}
