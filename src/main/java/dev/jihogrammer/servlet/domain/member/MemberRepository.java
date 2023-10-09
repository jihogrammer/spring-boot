package dev.jihogrammer.servlet.domain.member;

public interface MemberRepository {
    Member save(Member member);
    Member findById(Long id);
    Iterable<Member> findAll();
}
