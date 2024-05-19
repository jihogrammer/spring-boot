package dev.jihogrammer.spring.jdbc.member.application.port.in;

import dev.jihogrammer.spring.jdbc.member.MemberIntegrationTest;
import dev.jihogrammer.spring.jdbc.member.domain.Member;
import dev.jihogrammer.spring.jdbc.member.domain.exception.MemberException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DuplicateKeyException;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class JdbcTemplateSendMoneyPortTest extends MemberIntegrationTest {

    @Test
    @DisplayName("정상 이체")
    void normalSending() {
        // given
        var sender = jdbcTemplateMemberPort.save(Member.of(UUID.randomUUID(), 3000));
        var receiver = jdbcTemplateMemberPort.save(Member.of(UUID.randomUUID(), 1000));
        var money = 1400;
        var command = new SendMoneyCommand(sender.id(), receiver.id(), money);

        // when
        jdbcTemplateSendMoneyPort.sendMoney(command);

        // then
        assertThat(jdbcTemplateMemberPort.findById(sender.id()).money()).isEqualTo(sender.money() - money);
        assertThat(jdbcTemplateMemberPort.findById(receiver.id()).money()).isEqualTo(receiver.money() + money);
    }

    @Test
    @DisplayName("최악 이체")
    void theWorstSending() {
        // given
        var sender = jdbcTemplateMemberPort.save(Member.of(UUID.randomUUID(), 1000));
        var receiver = jdbcTemplateMemberPort.save(Member.of(UUID.randomUUID(), 900));
        var money = 1100;
        var command = new SendMoneyCommand(sender.id(), receiver.id(), money);

        // when
        ThrowingCallable when = () -> {
            try {
                jdbcTemplateSendMoneyPort.sendMoney(command);
            } catch (Throwable e) {
                log.error("EXPECTED EXCEPTION", e);
                throw e;
            }
        };

        // then
        assertThatThrownBy(when).isInstanceOf(MemberException.class);
        assertThat(jdbcTemplateMemberPort.findById(sender.id()).money()).isEqualTo(sender.money());
        assertThat(jdbcTemplateMemberPort.findById(receiver.id()).money()).isEqualTo(receiver.money());
    }

    @Test
    @DisplayName("키 중복 에러")
    void duplicatedKey() {
        // given
        final UUID uuid = UUID.randomUUID();

        // when
        ThrowingCallable when = () -> {
            try {
                jdbcTemplateMemberPort.save(Member.of(uuid, 1000));
                jdbcTemplateMemberPort.save(Member.of(uuid, 900));
            } catch (Throwable e) {
                log.error("EXPECTED EXCEPTION", e);
                throw e;
            }
        };

        // then
        assertThatThrownBy(when).isInstanceOf(DuplicateKeyException.class);
    }

}
