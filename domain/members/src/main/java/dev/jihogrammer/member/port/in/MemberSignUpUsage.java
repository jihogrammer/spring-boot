package dev.jihogrammer.member.port.in;

import dev.jihogrammer.member.Member;

public interface MemberSignUpUsage {

    Member signUp(MemberSignUpCommand command);

}
