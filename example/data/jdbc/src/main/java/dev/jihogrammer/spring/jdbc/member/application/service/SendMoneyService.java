package dev.jihogrammer.spring.jdbc.member.application.service;

import dev.jihogrammer.spring.jdbc.member.application.port.in.SendMoneyCommand;
import dev.jihogrammer.spring.jdbc.member.application.port.in.SendMoneyPort;
import dev.jihogrammer.spring.jdbc.member.application.port.out.MemberPort;
import dev.jihogrammer.spring.jdbc.member.domain.Member;
import dev.jihogrammer.spring.jdbc.member.domain.exception.MemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class SendMoneyService implements SendMoneyPort {

    private final MemberPort memberPort;

    @Override
    public void sendMoney(final SendMoneyCommand command) {
        if (command == null) {
            throw new MemberException("SendMoneyCommand is null.");
        }

        final var sender = this.memberPort.findById(command.senderId());
        final var receiver = this.memberPort.findById(command.receiverId());

        final var updatedSender = this.memberPort.update(new Member(sender.id(), sender.money() - command.money()));
        final var updatedReceiver = this.memberPort.update(new Member(receiver.id(), receiver.money() + command.money()));

        log.info("Success to send money. command={}; senderMoney=[{} -> {}]; receiverMoney=[{} -> {}]",
            command,
            sender.money(),
            updatedSender.money(),
            receiver.money(),
            updatedReceiver.money());
    }

}
