package dev.jihogrammer.members.port.out;

import dev.jihogrammer.domain.members.model.SignUpCommand;
import dev.jihogrammer.domain.members.port.out.Members;
import dev.jihogrammer.domain.members.intrastructure.adaptor.out.SingletonInMemoryMemberRepository;
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
        var command = SignUpCommand.builder().name("hello").build();

        // when
        var member = this.members.save(command);

        // then
        assertThat(member.username()).isEqualTo(command.username());
    }

    @Test
    void findById() {
        // given
        var member = this.members.save(SignUpCommand.builder().name("hello").build());

        // when
        var foundMember = this.members.findById(member.id());

        // then
        assertThat(foundMember).isEqualTo(member);
    }

    @Test
    void findAll() {
        // given
        var member1 = this.members.save(SignUpCommand.builder().name("hello").build());
        var member2 = this.members.save(SignUpCommand.builder().name("world").build());
        var member3 = this.members.save(SignUpCommand.builder().name("jihogrammer").build());

        // when
        var foundMembers = this.members.findAll();

        // then
        assertThat(foundMembers).contains(member1, member2, member3);
    }

    @Test
    void findByName() {
        // given
        var name = "jihogrammer";
        var expectedMember = this.members.save(SignUpCommand.builder().name(name).build());

        // when
        var optionalMember = this.members.findByUsername(name);

        // then
        assertThat(optionalMember).isPresent();
        assertThat(optionalMember.get()).isEqualTo(expectedMember);
    }

}
