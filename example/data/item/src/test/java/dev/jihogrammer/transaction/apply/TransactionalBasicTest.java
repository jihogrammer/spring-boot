package dev.jihogrammer.transaction.apply;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class TransactionalBasicTest {

    @Autowired
    Service service;

    @Test
    void checkProxy() {
        log.info("serviceClass={}", this.service.getClass());
        assertThat(AopUtils.isAopProxy(this.service)).isTrue();
    }

    @Test
    void transactional() {
        assertThat(this.service.transactional()).isTrue();
    }

    @Test
    void nonTransactional() {
        assertThat(this.service.nonTransactional()).isFalse();
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

        @Transactional
        boolean transactional() {
            final var result = TransactionSynchronizationManager.isActualTransactionActive();

            log.info("transactional() activeStatus={}", result);

            return result;
        }

        boolean nonTransactional() {
            final var result = TransactionSynchronizationManager.isActualTransactionActive();

            log.info("nonTransactional() activeStatus={}", result);

            return result;
        }

    }

}
