package dev.jihogrammer.spring.jdbc.member.application.service;

import dev.jihogrammer.spring.jdbc.member.application.port.in.SendMoneyCommand;
import dev.jihogrammer.spring.jdbc.member.application.port.in.SendMoneyPort;
import dev.jihogrammer.spring.jdbc.member.application.port.out.MemberPort;
import dev.jihogrammer.spring.jdbc.member.domain.Member;
import dev.jihogrammer.spring.jdbc.member.domain.exception.MemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
class TransactionalSendMoneyService implements SendMoneyPort {

    private final MemberPort memberPort;

    @Override
    @Transactional
    public void sendMoney(final SendMoneyCommand command) {
        if (command == null) {
            throw new MemberException("SendMoneyCommand is null.");
        }

        final var sender = this.memberPort.findById(command.senderId());
        final var updatedSender = new Member(sender.id(), sender.money() - command.money());
        this.memberPort.update(updatedSender);

        final var receiver = this.memberPort.findById(command.receiverId());
        final var updatedReceiver = new Member(receiver.id(), receiver.money() + command.money());
        this.memberPort.update(updatedReceiver);

        log.info("Success to send money. command={}; senderMoney=[{} -> {}]; receiverMoney=[{} -> {}]",
            command,
            sender.money(),
            updatedSender.money(),
            receiver.money(),
            updatedReceiver.money());
    }

}
