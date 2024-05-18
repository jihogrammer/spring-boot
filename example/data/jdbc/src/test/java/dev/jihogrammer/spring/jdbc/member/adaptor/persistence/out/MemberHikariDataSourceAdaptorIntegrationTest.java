package dev.jihogrammer.spring.jdbc.member.adaptor.persistence.out;

import dev.jihogrammer.spring.jdbc.member.MemberIntegrationTest;
import dev.jihogrammer.spring.jdbc.member.domain.Member;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class MemberHikariDataSourceAdaptorIntegrationTest extends MemberIntegrationTest {

    @Test
    void save() {
        // given
        var member = Member.of(UUID.randomUUID(), 0);

        // when
        var savedMember = memberPort.save(member);

        // then
        assertThat(savedMember).isEqualTo(member);
    }

    @Test
    void findById() {
        // given
        var savedMember = memberPort.save(Member.of(UUID.randomUUID(), 0));

        // when
        var foundMember = memberPort.findById(savedMember.id());

        // then
        assertThat(foundMember).isEqualTo(savedMember);
    }

    @Test
    void update() {
        // given
        var savedMember = memberPort.save(Member.of(UUID.randomUUID(), 0));
        var expectedMember = new Member(savedMember.id(), 1000);

        // when
        var updatedMember = memberPort.update(expectedMember);

        // then
        assertThat(updatedMember).isEqualTo(expectedMember);
    }

    @Test
    void delete() {
        // given
        var member = memberPort.save(Member.of(UUID.randomUUID(), 0));

        // when
        boolean isDeleted = memberPort.delete(member.id());

        // then
        assertThat(isDeleted).isTrue();
    }

}
