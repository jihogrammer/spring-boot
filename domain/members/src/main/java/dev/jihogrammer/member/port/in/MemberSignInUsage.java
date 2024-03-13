package dev.jihogrammer.member.port.in;

import dev.jihogrammer.member.model.Member;

public interface MemberSignInUsage {

    Member signIn(String name, String password);

}
