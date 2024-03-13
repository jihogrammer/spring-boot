package dev.jihogrammer.member.port.in;

import dev.jihogrammer.member.model.Member;
import dev.jihogrammer.member.model.MemberSignUpCommand;

public interface MemberSignUpUsage {

    Member signUp(MemberSignUpCommand command);

}
