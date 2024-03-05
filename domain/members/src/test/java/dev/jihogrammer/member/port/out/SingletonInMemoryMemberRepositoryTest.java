package dev.jihogrammer.member.port.out;

import dev.jihogrammer.member.model.MemberSignUpCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SingletonInMemoryMemberRepositoryTest {

    Members members;

    @BeforeEach
    void setUp() {
        this.members = SingletonInMemoryMemberRepository.getInstance();
        ((SingletonInMemoryMemberRepository) this.members).clear();
    }

    @Test
    void register() {
        // given
        var command = MemberSignUpCommand.builder().name("hello").build();

        // when
        var member = this.members.save(command);

        // then
        assertThat(member.name()).isEqualTo(command.name());
    }

    @Test
    void findById() {
        // given
        var member = this.members.save(MemberSignUpCommand.builder().name("hello").build());

        // when
        var foundMember = this.members.findById(member.id());

        // then
        assertThat(foundMember).isEqualTo(member);
    }

    @Test
    void findAll() {
        // given
        var member1 = this.members.save(MemberSignUpCommand.builder().name("hello").build());
        var member2 = this.members.save(MemberSignUpCommand.builder().name("world").build());
        var member3 = this.members.save(MemberSignUpCommand.builder().name("jihogrammer").build());

        // when
        var foundMembers = this.members.findAll();

        // then
        assertThat(foundMembers).contains(member1, member2, member3);
    }

    @Test
    void findByName() {
        // given
        var name = "jihogrammer";
        var expectedMember = this.members.save(MemberSignUpCommand.builder().name(name).build());

        // when
        var optionalMember = this.members.findByName(name);

        // then
        assertThat(optionalMember).isPresent();
        assertThat(optionalMember.get()).isEqualTo(expectedMember);
    }

}
