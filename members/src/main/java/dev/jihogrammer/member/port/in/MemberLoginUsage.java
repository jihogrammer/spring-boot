package dev.jihogrammer.member.port.in;

import dev.jihogrammer.member.Member;

public interface MemberLoginUsage {
    Member login(String name, String password);
}
