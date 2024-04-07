package dev.jihogrammer.member.adpator.out;

import dev.jihogrammer.member.exception.MemberException;
import dev.jihogrammer.member.model.MemberSaveCommand;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.assertj.core.api.Assertions.*;

class InMemoryMemberRepositoryTest {

    static InMemoryMemberRepository members;

    @BeforeAll
    static void setUpClass() {
        members = new InMemoryMemberRepository();
    }

    @AfterEach
    void tearDown() {
        members.clear();
    }

    @Test
    void save() {
        // given
        var command = MemberSaveCommand.builder().name("jihogrammer").email("hello").build();

        // when
        var member = members.save(command);

        // then
        assertThat(member.name()).isEqualTo(command.name());
        assertThat(member.email()).isEqualTo(command.email());
    }

    @Test
    void findById() {
        // given
        var member = members.save(MemberSaveCommand.builder().name("hello").email("world").build());

        // when
        var foundMember = members.findById(member.id()).orElseThrow();

        // then
        assertThat(foundMember).isEqualTo(member);
    }

    @Test
    void findAll() {
        // given
        var member1 = members.save(MemberSaveCommand.builder().name("name1").email("email1").build());
        var member2 = members.save(MemberSaveCommand.builder().name("name2").email("email2").build());
        var member3 = members.save(MemberSaveCommand.builder().name("name3").email("email3").build());

        // when
        var foundMembers = members.findAll();

        // then
        assertThat(foundMembers).contains(member1, member2, member3);
    }

    @Test
    void findByEmailAndPassword() {
        // given
        var email = "hello";
        var password = "world";
        var command = MemberSaveCommand.builder().name("jihogrammer").email(email).password(password).build();
        var expectedMember = members.save(command);

        // when
        var optionalMember = members.findByEmailAndPassword(email, password);

        // then
        assertThat(optionalMember).isPresent();
        assertThat(optionalMember.get()).isEqualTo(expectedMember);
    }

    @ParameterizedTest
    @NullSource
    void saveWithNullCommand(final MemberSaveCommand nullCommand) {
        // when
        ThrowingCallable when = () -> members.save(nullCommand);

        // then
        assertThatNullPointerException().isThrownBy(when);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void saveWithBlankName(final String blankName) {
        // given
        var command = MemberSaveCommand.builder().name(blankName).email("hello").build();

        // when
        ThrowingCallable when = () -> members.save(command);

        // then
        assertThatThrownBy(when).isInstanceOf(MemberException.class);
    }

    @ParameterizedTest
    @NullSource
    @EmptySource
    void saveWithBlankEmail(final String blankEmail) {
        // given
        var command = MemberSaveCommand.builder().name("jihogrammer").email(blankEmail).build();

        // when
        ThrowingCallable when = () -> members.save(command);

        // then
        assertThatThrownBy(when).isInstanceOf(MemberException.class);
    }

}
