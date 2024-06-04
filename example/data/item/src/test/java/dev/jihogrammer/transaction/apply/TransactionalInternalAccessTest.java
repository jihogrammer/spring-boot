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
class TransactionalInternalAccessTest {

    @Autowired
    Service service;

    @Test
    void external() {
        // internal 메서드에 선언된 Transactional 선언이 동작하지 않는다.
        assertThat(this.service.external()).isFalse();
    }

    @Test
    void internal() {
        assertThat(this.service.internal()).isTrue();
    }

    @TestConfiguration
    static class Config {

        @Bean
        Service service() {
            return new Service();
        }

    }

    @Slf4j
    static class Service {

        boolean external() {
            final var result = TransactionSynchronizationManager.isActualTransactionActive();

            log.info("external() activeStatus={}; isReadOnly={};",
                    result,
                    TransactionSynchronizationManager.isCurrentTransactionReadOnly());

            // call internal method
            return this.internal();
        }

        @Transactional
        boolean internal() {
            final var result = TransactionSynchronizationManager.isActualTransactionActive();

            log.info("internal() activeStatus={}; isReadOnly={};",
                    result,
                    TransactionSynchronizationManager.isCurrentTransactionReadOnly());

            return result;
        }

    }

}
