package dev.jihogrammer.member.port.in;

import dev.jihogrammer.member.Member;

public interface MemberRegisterUsage {

    Member register(MemberRegisterCommand command);

}
