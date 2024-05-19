package dev.jihogrammer.spring.jdbc.member.application.service;

import dev.jihogrammer.spring.jdbc.member.application.port.in.SendMoneyCommand;
import dev.jihogrammer.spring.jdbc.member.application.port.in.SendMoneyPort;
import dev.jihogrammer.spring.jdbc.member.application.port.out.MemberPort;
import dev.jihogrammer.spring.jdbc.member.domain.Member;
import dev.jihogrammer.spring.jdbc.member.domain.exception.MemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

@Slf4j
@RequiredArgsConstructor
class TransactionManagerSendMoneyService implements SendMoneyPort {

    private final MemberPort memberPort;

    private final PlatformTransactionManager transactionManager;

    @Override
    public void sendMoney(final SendMoneyCommand command) {
        if (command == null) {
            throw new MemberException("SendMoneyCommand is null.");
        }

        final var status = this.transactionManager.getTransaction(new DefaultTransactionAttribute());

        try {
            detailSendMoney(command);

            this.transactionManager.commit(status);
        } catch (Throwable e) {
            log.error("Failed to send money.", e);
            this.transactionManager.rollback(status);
            throw new MemberException(e);
        }
    }

    private void detailSendMoney(final SendMoneyCommand command) {
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
