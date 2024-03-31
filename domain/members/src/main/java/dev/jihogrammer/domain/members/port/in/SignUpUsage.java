package dev.jihogrammer.domain.members.port.in;

import dev.jihogrammer.domain.members.exception.MemberException;
import dev.jihogrammer.domain.members.model.Member;
import dev.jihogrammer.domain.members.model.SignUpCommand;

public interface SignUpUsage {

    Member signUp(SignUpCommand command) throws MemberException;

}
