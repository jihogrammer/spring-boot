package dev.jihogrammer.domain.members.port.out;

import dev.jihogrammer.domain.members.model.SignUpCommand;
import dev.jihogrammer.domain.members.port.out.InMemoryMemberRepository;
import dev.jihogrammer.domain.members.port.out.Members;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryMemberRepositoryTest {

    Members members;

    @BeforeEach
    void setUp() {
        this.members = new InMemoryMemberRepository();
    }

    @Test
    void register() {
        // given
        var command = SignUpCommand.builder().name("hello").password("world").build();

        // when
        var member = this.members.save(command);

        // then
        assertThat(member.username()).isEqualTo(command.username());
    }

    @Test
    void findById() {
        // given
        var member = this.members.save(SignUpCommand.builder().name("hello").password("world").build());

        // when
        var foundMember = this.members.findById(member.id());

        // then
        assertThat(foundMember).isEqualTo(member);
    }

    @Test
    void findAll() {
        // given
        var member1 = this.members.save(SignUpCommand.builder().name("hello").password("world").build());
        var member2 = this.members.save(SignUpCommand.builder().name("dev").password("jihogrammer").build());
        var member3 = this.members.save(SignUpCommand.builder().name("domain").password("members").build());

        // when
        var foundMembers = this.members.findAll();

        // then
        assertThat(foundMembers).contains(member1, member2, member3);
    }

    @Test
    void findByName() {
        // given
        var command = SignUpCommand.builder().name("dev").password("jihogrammer").build();
        var expectedMember = this.members.save(command);

        // when
        var optionalMember = this.members.findByUsername(command.username());

        // then
        assertThat(optionalMember).isPresent();
        assertThat(optionalMember.get()).isEqualTo(expectedMember);
    }

}
