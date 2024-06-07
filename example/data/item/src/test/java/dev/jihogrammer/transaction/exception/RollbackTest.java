package dev.jihogrammer.transaction.exception;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@SpringBootTest
class RollbackTest {

    @Autowired Service service;

    @Test
    void runtimeException() {
        try {
            service.runtimeException();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            assertThat(e).isInstanceOf(RuntimeException.class);
        }
    }

    @Test
    void checkedException() {
        try {
            service.checkedException();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            assertThat(e).isInstanceOf(Exception.class);
        }
    }

    @Test
    void rollbackForCheckedException() {
        try {
            service.rollbackForCheckedException();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            assertThat(e).isInstanceOf(Exception.class);
        }
    }

    @TestConfiguration
    static class Config {

        @Bean
        Service service() {
            return new Service();
        }

    }

    @Slf4j
    @Transactional
    static class Service {

        void runtimeException() {
            log.info("runtimeException()");
            throw new RuntimeException("should rollback");
        }

        void checkedException() throws Exception {
            log.info("checkedException()");
            throw new Exception("should commit");
        }

        @Transactional(rollbackFor = Exception.class)
        void rollbackForCheckedException() throws Exception {
            log.info("rollbackForCheckedException()");
            throw new Exception("should rollback");
        }

    }

}
