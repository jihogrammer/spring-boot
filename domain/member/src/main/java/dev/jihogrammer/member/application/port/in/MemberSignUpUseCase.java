package dev.jihogrammer.member.application.port.in;

import dev.jihogrammer.member.domain.Member;

public interface MemberSignUpUseCase {

    Member signUp(MemberSignUpCommand command);

}
