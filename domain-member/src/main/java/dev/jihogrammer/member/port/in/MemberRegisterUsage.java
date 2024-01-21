package dev.jihogrammer.member.port.in;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.model.in.MemberRegisterCommand;

public interface MemberRegisterUsage {
    Member register(MemberRegisterCommand command);
}
