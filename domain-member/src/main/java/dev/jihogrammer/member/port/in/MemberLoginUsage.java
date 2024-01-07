package dev.jihogrammer.member.port.in;

import dev.jihogrammer.member.Member;

import java.util.NoSuchElementException;

public interface MemberLoginUsage {
    Member login(String username, String password);
}
