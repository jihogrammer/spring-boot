package dev.jihogrammer.member.port.out;

import dev.jihogrammer.member.Member;
import dev.jihogrammer.member.model.in.MemberRegisterCommand;

public interface Members {
    Member register(MemberRegisterCommand command);
}
