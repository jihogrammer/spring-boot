package dev.jihogrammer.transaction.apply;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@SpringBootTest
class TransactionalAtInitializationTest {

    @Autowired Service service;

    @Test
    void init() {
        // EXPECTED LOG MESSAGE: initUsingPostConstruct() isActualTransactionActive=false
        // @PostConstruct 선언된 메서드는 스프링 컨테이너에서 자동으로 실행함
        // 객체 생성 > PostConstruct 실행 > AOP 적용
        // 위와 같은 순서로 적용되기 때문에 실제 PostConstruct 메서드로서 수행 시 Transactional 설정이 적용되지 않는다.

        // EXPECTED LOG MESSAGE: initUsingApplicationReadyEvent() isActualTransactionActive=true
        // 반면, EventListener 수행을 통한 초기화 작업은 스프링 컨테이너가 완전히 준비된 시점에 호출된다.
        // 객체 생성 > PostConstruct 실행 > AOP 적용 > ApplicationReadyEvent 발생
        // 따라서 Transactional 설정이 적용된다.
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

        @PostConstruct
        @Transactional
        void initUsingPostConstruct() {
            log.info("initUsingPostConstruct() isActualTransactionActive={}",
                    TransactionSynchronizationManager.isActualTransactionActive());
        }

        @EventListener(ApplicationReadyEvent.class)
        @Transactional
        void initUsingApplicationReadyEvent() {
            log.info("initUsingApplicationReadyEvent() isActualTransactionActive={}",
                    TransactionSynchronizationManager.isActualTransactionActive());
        }

    }

}
