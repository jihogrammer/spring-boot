package dev.jihogrammer.member;

import dev.jihogrammer.member.model.Member;

public interface Members {
    Member save(Member member);
    Member findById(Long id);
    Iterable<Member> findAll();
}
