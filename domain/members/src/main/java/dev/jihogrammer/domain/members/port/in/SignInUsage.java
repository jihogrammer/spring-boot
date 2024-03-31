package dev.jihogrammer.domain.members.port.in;

import dev.jihogrammer.domain.members.exception.MemberException;
import dev.jihogrammer.domain.members.exception.MemberValidationException;
import dev.jihogrammer.domain.members.model.Member;
import dev.jihogrammer.domain.members.model.SignInCommand;

public interface SignInUsage {

    Member signIn(SignInCommand command) throws MemberException;

}
