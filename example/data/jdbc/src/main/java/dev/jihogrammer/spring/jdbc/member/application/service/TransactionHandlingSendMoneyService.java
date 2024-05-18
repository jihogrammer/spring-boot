package dev.jihogrammer.spring.jdbc.member.application.service;

import dev.jihogrammer.spring.jdbc.member.application.port.in.SendMoneyCommand;
import dev.jihogrammer.spring.jdbc.member.application.port.in.SendMoneyPort;
import dev.jihogrammer.spring.jdbc.member.application.port.out.MemberPort;
import dev.jihogrammer.spring.jdbc.member.domain.Member;
import dev.jihogrammer.spring.jdbc.member.domain.exception.MemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.Connection;

import static java.util.Objects.nonNull;

@Slf4j
@RequiredArgsConstructor
class TransactionHandlingSendMoneyService implements SendMoneyPort {

    private final MemberPort memberPort;

    private final DataSource dataSource;

    @Override
    public void sendMoney(final SendMoneyCommand command) {
        if (command == null) {
            throw new MemberException("SendMoneyCommand is null.");
        }

        final Connection connection = this.getConnection();

        try {
            sendMoney(connection, command);
            connection.commit();
        } catch (Throwable e) {
            log.error("Failed to send money.", e);
            this.rollback(connection);
            throw new MemberException(e);
        } finally {
            this.close(connection);
        }
    }

    private void sendMoney(final Connection connection, final SendMoneyCommand command) {
        final var sender = this.memberPort.findById(connection, command.senderId());
        final var updatedSender = new Member(sender.id(), sender.money() - command.money());
        this.memberPort.update(connection, updatedSender);

        final var receiver = this.memberPort.findById(connection, command.receiverId());
        final var updatedReceiver = new Member(receiver.id(), receiver.money() + command.money());
        this.memberPort.update(connection, updatedReceiver);

        log.info("Success to send money. command={}; senderMoney=[{} -> {}]; receiverMoney=[{} -> {}]",
            command,
            sender.money(),
            updatedSender.money(),
            receiver.money(),
            updatedReceiver.money());
    }

    private Connection getConnection() {
        try {
            final var connection = this.dataSource.getConnection();

            // Turn off for transaction handling.
            connection.setAutoCommit(false);

            return connection;
        } catch (Throwable e) {
            log.error("Failed to get a connection.", e);
            throw new MemberException(e);
        }
    }

    private void commit(final Connection connection) {
        if (nonNull(connection)) {
            try {
                // Commit for transaction.
                connection.commit();
            } catch (Throwable e) {
                log.error("Failed to commit.", e);
                throw new MemberException("Failed to commit.", e);
            }
        }
    }

    private void rollback(final Connection connection) {
        if (nonNull(connection)) {
            try {
                // Rollback for failed transaction.
                connection.rollback();
            } catch (Throwable e) {
                log.error("Failed to rollback.", e);
                throw new MemberException("Failed to rollback.", e);
            }
        }
    }

    private void close(final Connection connection) {
        if (nonNull(connection)) {
            try {
                // Turn on for connection pool.
                connection.setAutoCommit(true);
            } catch (Throwable e) {
                log.error("Failed to setAutoCommit.", e);
                throw new MemberException("Failed to setAutoCommit.", e);
            } finally {
                JdbcUtils.closeConnection(connection);
            }
        }
    }

}
