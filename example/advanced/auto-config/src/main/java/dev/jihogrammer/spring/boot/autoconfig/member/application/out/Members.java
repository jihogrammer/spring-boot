package dev.jihogrammer.spring.boot.autoconfig.member.application.out;

import dev.jihogrammer.spring.boot.autoconfig.member.domain.Member;
import dev.jihogrammer.spring.boot.autoconfig.member.domain.MemberId;

import java.util.List;

public interface Members {

    void save(Member member);

    Member findById(MemberId id);

    List<Member> findAll();

}
