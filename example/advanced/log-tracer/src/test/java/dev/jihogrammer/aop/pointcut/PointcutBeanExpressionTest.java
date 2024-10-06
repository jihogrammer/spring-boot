package dev.jihogrammer.aop.pointcut;

import dev.jihogrammer.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(PointcutBeanExpressionTest.BeanExpressionAspect.class)
class PointcutBeanExpressionTest {

    @BeforeEach
    void setUp() {
        BeanExpressionAspect.isExecuted.set(false);
    }

    @Test
    void checkProxy(@Autowired final MemberService memberService) {
        // when
        memberService.hello("jihogrammer");

        // then
        assertThat(BeanExpressionAspect.isExecuted.get()).isTrue();
    }

    @Slf4j
    @Aspect
    static class BeanExpressionAspect {
        static final ThreadLocal<Boolean> isExecuted = ThreadLocal.withInitial(() -> false);

        @Around("bean(memberService)")
        Object applyLogging(final ProceedingJoinPoint joinPoint) throws Throwable {
            try {
                log.info("[{}] {}", this.getClass().getSimpleName(), joinPoint.getSignature());
                return joinPoint.proceed();
            } finally {
                isExecuted.set(true);
            }
        }
    }

}
