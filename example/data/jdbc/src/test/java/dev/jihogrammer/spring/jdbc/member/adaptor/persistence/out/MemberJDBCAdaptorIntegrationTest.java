package dev.jihogrammer.spring.jdbc.member.adaptor.persistence.out;

import dev.jihogrammer.spring.jdbc.member.application.port.out.MemberPort;
import dev.jihogrammer.spring.jdbc.member.domain.Member;
import dev.jihogrammer.spring.jdbc.member.domain.MemberId;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = MemberJDBCAdaptorTestConfig.class)
class MemberJDBCAdaptorIntegrationTest {

    @Autowired
    MemberPort memberPort;

    @Test
    void save() {
        // given
        var member = new Member(new MemberId("hello"), 0);

        // when
        var savedMember = memberPort.save(member);

        // then
        assertThat(savedMember).isEqualTo(member);
    }

}
