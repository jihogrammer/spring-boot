package dev.jihogrammer.member.port.out;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.model.in.MemberRegisterCommand;
import dev.jihogrammer.member.model.vo.MemberId;

import java.util.Collection;
import java.util.Optional;

public interface Members {
    Member register(MemberRegisterCommand command);
    Collection<Member> findAll();
    Optional<Member> findById(MemberId memberId);
    Optional<Member> findByUsername(String username);
}
