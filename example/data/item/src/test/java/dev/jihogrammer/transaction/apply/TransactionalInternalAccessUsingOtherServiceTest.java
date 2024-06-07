package dev.jihogrammer.transaction.apply;

import lombok.RequiredArgsConstructor;
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
class TransactionalInternalAccessUsingOtherServiceTest {

    @Autowired
    ExternalService service;

    @Test
    void external() {
        // internalService#doSomething 메서드에 선언된 Transactional 선언이 동작한다.
        assertThat(this.service.doSomething()).isTrue();
    }

    @TestConfiguration
    static class Config {

        @Bean
        InternalService internalService() {
            return new InternalService();
        }

        @Bean
        ExternalService externalService(InternalService internalService) {
            return new ExternalService(internalService);
        }

    }

    @Slf4j
    @RequiredArgsConstructor
    static class ExternalService {

        private final InternalService internalService;

        boolean doSomething() {
            final var result = TransactionSynchronizationManager.isActualTransactionActive();

            log.info("{}#doSomething() activeStatus={}; isReadOnly={};",
                    this.getClass().getSimpleName(),
                    result,
                    TransactionSynchronizationManager.isCurrentTransactionReadOnly());

            return this.internalService.doSomething();
        }

    }

    @Slf4j
    static class InternalService {

        @Transactional
        boolean doSomething() {
            final var result = TransactionSynchronizationManager.isActualTransactionActive();

            log.info("{}#doSomething() activeStatus={}; isReadOnly={};",
                    this.getClass().getSimpleName(),
                    result,
                    TransactionSynchronizationManager.isCurrentTransactionReadOnly());

            return result;
        }

    }

}
