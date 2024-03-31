package dev.jihogrammer.domain.members.port.out;

import dev.jihogrammer.domain.members.model.Member;
import dev.jihogrammer.domain.members.model.MemberId;
import dev.jihogrammer.domain.members.model.SignUpCommand;

import java.util.Collection;
import java.util.Optional;

public interface Members {

    Member save(SignUpCommand command);

    Member findById(MemberId id);

    Collection<Member> findAll();

    Optional<Member> findByUsername(String username);

}
