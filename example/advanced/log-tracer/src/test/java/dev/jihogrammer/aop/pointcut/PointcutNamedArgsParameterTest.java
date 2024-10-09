package dev.jihogrammer.aop.pointcut;

import dev.jihogrammer.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(PointcutNamedArgsParameterTest.ExtractingArgsAspect.class)
class PointcutNamedArgsParameterTest {

    static ThreadLocal<String> PARAM = ThreadLocal.withInitial(() -> null);

    @Test
    void args(@Autowired final MemberService memberService) {
        // given
        var someone = "jihogrammer";

        // when
        memberService.hello(someone);

        // then
        assertThat(PARAM.get()).isEqualTo(someone);
    }

    @Aspect
    @Slf4j
    static class ExtractingArgsAspect {
        @Pointcut("execution(* dev.jihogrammer.aop.member..*.*(..))")
        private void runningMembers() {
        }

        @Around("runningMembers() && args(param)")
        Object extractUsingArgsExpression(final ProceedingJoinPoint joinPoint, final String param) throws Throwable {
            try {
                log.info("[{}] {}", this.getClass().getSimpleName(), joinPoint.getSignature());
                return joinPoint.proceed();
            } finally {
                PARAM.set(param);
            }
        }
    }

}
