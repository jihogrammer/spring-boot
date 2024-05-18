package dev.jihogrammer.spring.jdbc.member.application.port.in;

import dev.jihogrammer.spring.jdbc.member.MemberIntegrationTest;
import dev.jihogrammer.spring.jdbc.member.domain.Member;
import dev.jihogrammer.spring.jdbc.member.domain.exception.MemberException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class TransactionalSendMoneyPortTest extends MemberIntegrationTest {

    @Test
    void checkProxy() {
        assertThat(AopUtils.isAopProxy(memberPort)).isFalse();
        assertThat(AopUtils.isAopProxy(sendMoneyPort)).isTrue();
    }

    @Test
    @DisplayName("정상 이체")
    void normalSending() {
        // given
        var sender = memberPort.save(Member.of(UUID.randomUUID(), 3000));
        var receiver = memberPort.save(Member.of(UUID.randomUUID(), 1000));
        var money = 1400;
        var command = new SendMoneyCommand(sender.id(), receiver.id(), money);

        // when
        sendMoneyPort.sendMoney(command);

        // then
        assertThat(memberPort.findById(sender.id()).money()).isEqualTo(sender.money() - money);
        assertThat(memberPort.findById(receiver.id()).money()).isEqualTo(receiver.money() + money);
    }

    @Test
    @DisplayName("최악 이체")
    void theWorstSending() {
        // given
        var sender = memberPort.save(Member.of(UUID.randomUUID(), 1000));
        var receiver = memberPort.save(Member.of(UUID.randomUUID(), 900));
        var money = 1100;
        var command = new SendMoneyCommand(sender.id(), receiver.id(), money);

        // when
        ThrowingCallable when = () -> {
            try {
                sendMoneyPort.sendMoney(command);
            } catch (Throwable e) {
                log.error("EXPECTED EXCEPTION", e);
                throw e;
            }
        };

        // then
        assertThatThrownBy(when).isInstanceOf(MemberException.class);
        assertThat(memberPort.findById(sender.id()).money()).isEqualTo(sender.money());
        assertThat(memberPort.findById(receiver.id()).money()).isEqualTo(receiver.money());
    }

}
