package dev.jihogrammer.aop.pointcut;

import dev.jihogrammer.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

//@SpringBootTest(properties = "spring.aop.proxy-target-class=false") // JDK Dynamic Proxy 동작; 잘 사용하지 않음;
@SpringBootTest(properties = "spring.aop.proxy-target-class=true") // default; CGLIB Proxy 동작;
@Import(PointcutThisAndTargetExpressionComparisonTest.ThisAndTargetAspect.class)
class PointcutThisAndTargetExpressionComparisonTest {

    @Test
    void thisAndTargetExpression(@Autowired final MemberService memberService) {
        memberService.hello("jihogrammer");
    }

    @Aspect
    @Slf4j
    static class ThisAndTargetAspect {
        @Before("this(dev.jihogrammer.aop.member.MemberGreeting)")
        void thisInterface(final JoinPoint joinPoint) {
            log.info("[thisInterface] {}", joinPoint.getSignature());
        }

        @Before("target(dev.jihogrammer.aop.member.MemberGreeting)")
        void targetInterface(final JoinPoint joinPoint) {
            log.info("[targetInterface] {}", joinPoint.getSignature());
        }

        @Before("this(dev.jihogrammer.aop.member.MemberService)")
        void thisConcrete(final JoinPoint joinPoint) {
            log.info("[thisConcrete] {}", joinPoint.getSignature());
        }

        @Before("target(dev.jihogrammer.aop.member.MemberService)")
        void targetConcrete(final JoinPoint joinPoint) {
            log.info("[targetConcrete] {}", joinPoint.getSignature());
        }
    }

}
