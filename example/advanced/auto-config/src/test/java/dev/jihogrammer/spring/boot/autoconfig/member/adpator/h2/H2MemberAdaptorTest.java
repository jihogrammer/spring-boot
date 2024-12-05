package dev.jihogrammer.spring.boot.autoconfig.member.adpator.h2;

import dev.jihogrammer.spring.boot.autoconfig.member.application.out.Members;
import dev.jihogrammer.spring.boot.autoconfig.member.domain.Member;
import dev.jihogrammer.spring.boot.autoconfig.member.domain.MemberId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class H2MemberAdaptorTest {

    @Test
    @Transactional
    void test(@Autowired final Members members) {
        var member = new Member(new MemberId("A"), "memberA");

        members.save(member);

        var foundMember = members.findById(member.id());

        assertThat(foundMember).isEqualTo(member);
    }

}
