package dev.jihogrammer.aop.warning;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class InternalCallingTest {

    @BeforeEach
    void setUp() {
        LoggingAspect.isExecutedExternal.set(false);
        LoggingAspect.isExecutedInternal.set(false);
    }

    @Test
    void external(@Autowired final Service service) {
        // when
        service.external();

        // then
        assertThat(LoggingAspect.isExecutedExternal.get()).isTrue();
        assertThat(LoggingAspect.isExecutedInternal.get()).isFalse();
    }

    @Test
    void internal(@Autowired final Service service) {
        // when
        service.internal();

        // then
        assertThat(LoggingAspect.isExecutedExternal.get()).isFalse();
        assertThat(LoggingAspect.isExecutedInternal.get()).isTrue();
    }

    @Test
    void externalContext(@Autowired final ContextSolutionService service) {
        // when
        service.external();

        // then
        assertThat(LoggingAspect.isExecutedExternal.get()).isTrue();
        assertThat(LoggingAspect.isExecutedInternal.get()).isTrue();
    }

    @Test
    void externalProvider(@Autowired final ProviderSolutionService service) {
        // when
        service.external();

        // then
        assertThat(LoggingAspect.isExecutedExternal.get()).isTrue();
        assertThat(LoggingAspect.isExecutedInternal.get()).isTrue();
    }

    @Test
    void externalProvider(@Autowired final ExternalSolutionService service) {
        // when
        service.external();

        // then
        assertThat(LoggingAspect.isExecutedExternal.get()).isTrue();
        assertThat(LoggingAspect.isExecutedInternal.get()).isTrue();
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        Service service() {
            return new Service();
        }

        @Bean
        ContextSolutionService contextSolutionService(final ApplicationContext context) {
            return new ContextSolutionService(context);
        }

        @Bean
        ProviderSolutionService providerSolutionService(final ObjectProvider<ProviderSolutionService> provider) {
            return new ProviderSolutionService(provider);
        }

        @Bean
        ExternalSolutionService externalSolutionService(final InternalSolutionService service) {
            return new ExternalSolutionService(service);
        }

        @Bean
        InternalSolutionService internalSolutionService() {
            return new InternalSolutionService();
        }

        @Bean
        LoggingAspect aspect() {
            return new LoggingAspect();
        }
    }

    @Slf4j
    static class Service {
        void external() {
            log.info("[external]");
            this.internal();
        }

        void internal() {
            log.info("[internal]");
        }
    }

    @Slf4j
    @RequiredArgsConstructor
    static class ContextSolutionService {
        private final ApplicationContext context;

        void external() {
            log.info("[external]");
            context.getBean(ContextSolutionService.class).internal();
        }

        void internal() {
            log.info("[internal]");
        }
    }

    @Slf4j
    @RequiredArgsConstructor
    static class ProviderSolutionService {
        private final ObjectProvider<ProviderSolutionService> provider;

        void external() {
            log.info("[external]");
            provider.getObject().internal();
        }

        void internal() {
            log.info("[internal]");
        }
    }

    @Slf4j
    @RequiredArgsConstructor
    static class ExternalSolutionService {
        private final InternalSolutionService service;

        void external() {
            log.info("[external]");
            service.internal();
        }
    }

    @Slf4j
    @RequiredArgsConstructor
    static class InternalSolutionService {
        void internal() {
            log.info("[internal]");
        }
    }

    @Aspect
    @Slf4j
    static class LoggingAspect {
        static final ThreadLocal<Boolean> isExecutedExternal = ThreadLocal.withInitial(() -> false);
        static final ThreadLocal<Boolean> isExecutedInternal = ThreadLocal.withInitial(() -> false);

        @Before("execution(* dev.jihogrammer.aop.warning..*.external(..))")
        void applyLoggingToExternal(final JoinPoint joinPoint) {
            try {
                log.info("{}", joinPoint.getSignature());
            } finally {
                isExecutedExternal.set(true);
            }
        }

        @Before("execution(* dev.jihogrammer.aop.warning..*.internal(..))")
        void applyLoggingToInternal(final JoinPoint joinPoint) {
            try {
                log.info("{}", joinPoint.getSignature());
            } finally {
                isExecutedInternal.set(true);
            }
        }
    }

}
