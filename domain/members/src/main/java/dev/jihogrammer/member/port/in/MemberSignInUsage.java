package dev.jihogrammer.member.port.in;

import dev.jihogrammer.member.Member;

public interface MemberSignInUsage {

    Member signIn(String name, String password);

}
