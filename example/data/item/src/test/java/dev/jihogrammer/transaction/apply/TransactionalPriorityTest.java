package dev.jihogrammer.transaction.apply;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class TransactionalPriorityTest {

    @Autowired
    Service service;

    @Test
    void write() {
        assertThat(this.service.write()).isFalse();
    }

    @Test
    void read() {
        assertThat(this.service.read()).isTrue();
    }

    @TestConfiguration
    static class Config {

        @Bean
        Service service() {
            return new Service();
        }

    }

    @Slf4j
    @Transactional(readOnly = true)
    static class Service {

        @Transactional(readOnly = false)
        boolean write() {
            final var result = TransactionSynchronizationManager.isCurrentTransactionReadOnly();

            log.info("write() activeStatus={}; isReadOnly={};",
                    TransactionSynchronizationManager.isActualTransactionActive(),
                    result);

            return result;
        }

        boolean read() {
            final var result = TransactionSynchronizationManager.isCurrentTransactionReadOnly();

            log.info("read() activeStatus={}; isReadOnly={};",
                    TransactionSynchronizationManager.isActualTransactionActive(),
                    result);

            return result;
        }

    }

}
