package dev.jihogrammer.member.application.port.in;

import dev.jihogrammer.member.domain.Member;
import dev.jihogrammer.member.domain.model.MemberSignInCommand;

public interface MemberSignInUsage {

    Member signIn(MemberSignInCommand command);

}
