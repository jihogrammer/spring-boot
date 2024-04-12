package dev.jihogrammer.member.application.port.in;

import dev.jihogrammer.member.domain.Member;

public interface MemberSignInUseCase {

    Member signIn(MemberSignInCommand command);

}
