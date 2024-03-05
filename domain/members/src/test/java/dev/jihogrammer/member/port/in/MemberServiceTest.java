package dev.jihogrammer.member.port.in;

import dev.jihogrammer.member.model.MemberSignUpCommand;
import dev.jihogrammer.member.port.out.SingletonInMemoryMemberRepository;
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
        var command = MemberSignUpCommand.builder().name("jihogrammer").build();

        // when
        var member = this.memberService.signUp(command);

        // then
        assertThat(member.name()).isEqualTo(command.name());
    }

    @Test
    void signIn() {
        // given
        var name = "hello";
        var password = "world";
        this.memberService.signUp(MemberSignUpCommand.builder().name(name).password(password).build());

        // when
        var signedInMember = this.memberService.signIn(name, password);

        // then
        assertThat(signedInMember.name()).isEqualTo(name);
        assertThat(signedInMember.password()).isEqualTo(password);
    }

}
