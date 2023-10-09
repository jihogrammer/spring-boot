package dev.jihogrammer.member;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MemberTest {
    @Test
    void constructor() {
        // given
        Long id = 123L;
        String username = "username";
        int age = 45;
        Member member = new Member();
        // when
        member.setId(id);
        member.setUsername(username);
        member.setAge(age);
        // then
        assertThat(member.getId()).isEqualTo(id);
        assertThat(member.getUsername()).isEqualTo(username);
        assertThat(member.getAge()).isEqualTo(age);
    }

    @Test
    void constructorUsernameAndAge() {
        // given
        String username = "username";
        int age = 45;
        // when
        Member member = new Member(username, age);
        // then
        assertThat(member.getUsername()).isEqualTo(username);
        assertThat(member.getAge()).isEqualTo(age);
    }
}