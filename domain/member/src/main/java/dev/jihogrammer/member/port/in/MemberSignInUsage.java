package dev.jihogrammer.member.port.in;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.model.MemberSignInCommand;

public interface MemberSignInUsage {

    Member signIn(MemberSignInCommand command);

}
