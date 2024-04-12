package dev.jihogrammer.member.application.port.out;

import dev.jihogrammer.member.domain.Member;
import dev.jihogrammer.member.domain.model.MemberId;

import java.util.Collection;
import java.util.Optional;

public interface MemberPort {

    Member save(MemberSaveCommand command);

    Optional<Member> findById(MemberId id);

    Collection<Member> findAll();

    Optional<Member> findByEmailAndPassword(String email, String password);

}
