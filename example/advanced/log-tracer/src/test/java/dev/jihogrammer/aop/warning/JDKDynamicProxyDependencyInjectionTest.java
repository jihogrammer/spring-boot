package dev.jihogrammer.aop.warning;

import dev.jihogrammer.aop.member.MemberGreeting;
import dev.jihogrammer.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.ThrowableAssert;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@SpringBootTest(properties = "spring.aop.proxy-target-class=false") // config JDK Dynamic Proxy
@Slf4j
class JDKDynamicProxyDependencyInjectionTest {

    @Test
    void interfaceInjection(@Autowired final ApplicationContext context) {
        // given
        var memberGreeting = context.getBean(MemberGreeting.class);

        // when
        ThrowingCallable when = () -> memberGreeting.hello("jihogrammer");

        // then
        assertThatCode(when).doesNotThrowAnyException();
    }

    @Test
    void concreteInjection(@Autowired final ApplicationContext context) {
        // given
        var concreteClass = MemberService.class;

        // when
        ThrowingCallable when = () -> context.getBean(concreteClass);

        // then
        assertThatCode(when).isInstanceOf(NoSuchBeanDefinitionException.class);
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        TestAspect testAspect() {
            return new TestAspect();
        }
    }

    @Aspect
    @Slf4j
    static class TestAspect {
        @Before("execution(* dev.jihogrammer.aop.member..*(..))")
        void advice(final JoinPoint joinPoint) {
            log.info("advice - {}", joinPoint.getSignature());
        }
    }

}
