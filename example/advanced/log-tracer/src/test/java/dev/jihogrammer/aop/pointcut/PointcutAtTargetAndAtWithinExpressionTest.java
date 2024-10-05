package dev.jihogrammer.aop.pointcut;

import dev.jihogrammer.aop.member.annotation.ClassAop;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static dev.jihogrammer.aop.pointcut.PointcutAtTargetAndAtWithinExpressionTest.AtTargetAndAtWithinAspect.isExecutedAtTarget;
import static dev.jihogrammer.aop.pointcut.PointcutAtTargetAndAtWithinExpressionTest.AtTargetAndAtWithinAspect.isExecutedAtWithin;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PointcutAtTargetAndAtWithinExpressionTest {

    @AfterEach
    void tearDown() {
        isExecutedAtTarget.set(false);
        isExecutedAtWithin.set(false);
    }

    @Test
    void testProxy(@Autowired final Child child) {
        assertThat(AopUtils.isAopProxy(child)).isTrue();
    }

    @Test
    @DisplayName("@target 표현식은 상위 클래스의 동작까지 포함하지만, @within 표현식은 해당 클래스 내부에서만 동작한다.")
    void executeParentMethod(@Autowired final Child child) {
        // when
        child.parentMethod();

        // then
        assertThat(isExecutedAtTarget.get()).isTrue();
        assertThat(isExecutedAtWithin.get()).isFalse();
    }

    @Test
    void executeChildMethod(@Autowired final Child child) {
        // when
        child.childMethod();

        // then
        assertThat(isExecutedAtTarget.get()).isTrue();
        assertThat(isExecutedAtWithin.get()).isTrue();
    }

    static class Parent {
        void parentMethod() {
        }
    }

    @ClassAop
    static class Child extends Parent {
        void childMethod() {
        }
    }

    @Slf4j
    @Aspect
    static class AtTargetAndAtWithinAspect {
        static ThreadLocal<Boolean> isExecutedAtTarget = ThreadLocal.withInitial(() -> false);

        static ThreadLocal<Boolean> isExecutedAtWithin = ThreadLocal.withInitial(() -> false);

        @Pointcut("execution(* dev.jihogrammer..*(..))")
        private void appliedArea() {
        }

        @Pointcut("@target(dev.jihogrammer.aop.member.annotation.ClassAop)")
        private void atTarget() {
        }

        @Pointcut("@within(dev.jihogrammer.aop.member.annotation.ClassAop)")
        private void atWithin() {
        }

        @Around("appliedArea() && atTarget()")
        Object arTarget(final ProceedingJoinPoint joinPoint) throws Throwable {
            try {
                log.info("[@target] {}", joinPoint.getSignature());
                return joinPoint.proceed();
            } finally {
                isExecutedAtTarget.set(true);
            }
        }

        @Around("appliedArea() && atWithin()")
        Object atWithin(final ProceedingJoinPoint joinPoint) throws Throwable {
            try {
                log.info("[@within] {}", joinPoint.getSignature());
                return joinPoint.proceed();
            } finally {
                isExecutedAtWithin.set(true);
            }
        }

        /**
         * {@code args, @target, @within} 단독으로 사용할 수 없음에 유의.
         * spring 내부에서 정의하는 모든 bean 대상으로 proxy 적용하다가 final class 때문에 구동에 실패하게 된다.
         */
        // @Around("atTarget()")
        Object onlyTarget(final ProceedingJoinPoint joinPoint) throws Throwable {
            throw new UnsupportedOperationException("@target 단독 사용 불가");
        }
    }

    @TestConfiguration
    static class TestConfig {
        @Bean
        Child child() {
            return new Child();
        }

        @Bean
        AtTargetAndAtWithinAspect aspect() {
            return new AtTargetAndAtWithinAspect();
        }
    }

}
