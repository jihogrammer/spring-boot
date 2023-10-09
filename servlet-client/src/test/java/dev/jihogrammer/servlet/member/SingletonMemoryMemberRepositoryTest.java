package dev.jihogrammer.servlet.member;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.MemberRepository;
import dev.jihogrammer.servlet.member.SingletonMemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SingletonMemoryMemberRepositoryTest {
    static MemberRepository repository = SingletonMemoryMemberRepository.getInstance();

    @AfterEach
    void tearDown() {
        ((SingletonMemoryMemberRepository) repository).clearStore();
    }

    @Test
    void save() {
        // given
        Member member = new Member("jihogrammer", 30);
        // when
        Member savedMember = repository.save(member);
        // then
        assertThat(repository.findById(savedMember.getId())).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        // given
        Member memberA = repository.save(new Member("memberA", 10));
        Member memberB = repository.save(new Member("memberB", 20));
        // when
        List<Member> members = new ArrayList<>();
        repository.findAll().forEach(members::add);
        // then
        assertThat(members).hasSize(2);
        assertThat(members).contains(memberA, memberB);
    }
}