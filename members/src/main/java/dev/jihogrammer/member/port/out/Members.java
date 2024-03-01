package dev.jihogrammer.member.port.out;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.model.MemberId;
import dev.jihogrammer.member.port.in.MemberRegisterCommand;

import java.util.Collection;
import java.util.Optional;

public interface Members {

    Member register(MemberRegisterCommand command);

    Member findById(MemberId id);

    Collection<Member> findAll();

    Optional<Member> findByUsername(String name);

}
