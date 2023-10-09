package dev.jihogrammer.member;

public interface MemberRepository {
    Member save(Member member);
    Member findById(Long id);
    Iterable<Member> findAll();
}
