package dev.jihogrammer.member.application.port.in;

import dev.jihogrammer.member.domain.Member;
import dev.jihogrammer.member.domain.model.MemberSignUpCommand;

public interface MemberSignUpUsage {

    Member signUp(MemberSignUpCommand command);

}
