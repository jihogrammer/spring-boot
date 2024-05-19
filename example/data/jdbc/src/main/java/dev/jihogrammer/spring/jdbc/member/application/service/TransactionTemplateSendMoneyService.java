package dev.jihogrammer.spring.jdbc.member.application.service;

import dev.jihogrammer.spring.jdbc.member.application.port.in.SendMoneyCommand;
import dev.jihogrammer.spring.jdbc.member.application.port.in.SendMoneyPort;
import dev.jihogrammer.spring.jdbc.member.application.port.out.MemberPort;
import dev.jihogrammer.spring.jdbc.member.domain.Member;
import dev.jihogrammer.spring.jdbc.member.domain.exception.MemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Slf4j
class TransactionTemplateSendMoneyService implements SendMoneyPort {

    private final MemberPort memberPort;

    private final TransactionTemplate transactionTemplate;

    TransactionTemplateSendMoneyService(final MemberPort memberPort, final PlatformTransactionManager transactionManager) {
        this.memberPort = memberPort;
        this.transactionTemplate = new TransactionTemplate(transactionManager);
    }

    @Override
    public void sendMoney(final SendMoneyCommand command) {
        if (command == null) {
            throw new MemberException("SendMoneyCommand is null.");
        }

        // TransactionTemplate callback 메서드에서는 UncheckedException(RuntimeException) 예외일 때만 롤백을 수행한다.
        // 여기서는 MemberPort 내부에서 MemberException(RuntimeException) 예외를 발생시키기 때문에 별도 처리가 없다.
        this.transactionTemplate.executeWithoutResult(transactionStatus -> detailSendMoney(command));
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
