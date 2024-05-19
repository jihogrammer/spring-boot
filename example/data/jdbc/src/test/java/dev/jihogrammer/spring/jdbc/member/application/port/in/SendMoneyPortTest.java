package dev.jihogrammer.spring.jdbc.member.application.port.in;

import dev.jihogrammer.spring.jdbc.member.MemberIntegrationTest;
import dev.jihogrammer.spring.jdbc.member.application.port.out.MemberPort;
import dev.jihogrammer.spring.jdbc.member.application.service.MemberServiceFactory;
import dev.jihogrammer.spring.jdbc.member.domain.Member;
import dev.jihogrammer.spring.jdbc.member.domain.exception.MemberException;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class SendMoneyPortTest extends MemberIntegrationTest {

    @Test
    @DisplayName("정상 이체")
    void normalSending() {
        // given
        var sender = dataSourceMemberPort.save(Member.of(UUID.randomUUID(), 3000));
        var receiver = dataSourceMemberPort.save(Member.of(UUID.randomUUID(), 1000));
        var money = 1400;
        var command = new SendMoneyCommand(sender.id(), receiver.id(), money);

        // when
        unstableSendMoneyPort.sendMoney(command);

        // then
        assertThat(dataSourceMemberPort.findById(sender.id()).money()).isEqualTo(sender.money() - money);
        assertThat(dataSourceMemberPort.findById(receiver.id()).money()).isEqualTo(receiver.money() + money);
    }

    @Test
    @DisplayName("최악 이체")
    void theWorstSending() {
        // given
        var sender = dataSourceMemberPort.save(Member.of(UUID.randomUUID(), 1000));
        var receiver = dataSourceMemberPort.save(Member.of(UUID.randomUUID(), 900));
        var mistreatedSender = new Member(sender.id(), 900);
        var mistreatedReceiver = new Member(receiver.id(), 1000);
        var money = 100;
        var command = new SendMoneyCommand(sender.id(), receiver.id(), money);

        var badMemberPort = Mockito.mock(MemberPort.class);
        var badSendMoneyPort = new MemberServiceFactory().unstableSendMoneyPort(badMemberPort);

        Mockito.when(badMemberPort.findById(sender.id())).thenReturn(dataSourceMemberPort.findById(sender.id()));
        Mockito.when(badMemberPort.findById(receiver.id())).thenReturn(dataSourceMemberPort.findById(receiver.id()));
        Mockito.when(badMemberPort.update(mistreatedSender)).thenReturn(dataSourceMemberPort.update(mistreatedSender));
        Mockito.when(badMemberPort.update(mistreatedReceiver)).thenThrow(new MemberException("What a mistreated."));

        // when
        ThrowingCallable when = () -> {
            try {
                badSendMoneyPort.sendMoney(command);
            } catch (Throwable e) {
                log.error("Failed to send money.", e);
                throw e;
            }
        };

        // then
        assertThatThrownBy(when).isInstanceOf(MemberException.class);
        assertThat(dataSourceMemberPort.findById(sender.id()).money()).isEqualTo(sender.money() - money);
        assertThat(dataSourceMemberPort.findById(receiver.id()).money()).isEqualTo(receiver.money());
    }

}
