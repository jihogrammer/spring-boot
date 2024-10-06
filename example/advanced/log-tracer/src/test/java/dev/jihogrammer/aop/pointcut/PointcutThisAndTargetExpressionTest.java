package dev.jihogrammer.aop.pointcut;

import dev.jihogrammer.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(PointcutThisAndTargetExpressionTest.ThisAndTargetAspect.class)
class PointcutThisAndTargetExpressionTest {

    @Test
    void thisAndTargetExpression(@Autowired final MemberService memberService) {
        memberService.hello("jihogrammer");
    }

    @Aspect
    @Slf4j
    static class ThisAndTargetAspect {
        @Pointcut("execution(* dev.jihogrammer.aop.member..*.*(..))")
        private void runningMembers() {
        }

        @Before("runningMembers() && this(obj)")
        void thisExpression(final MemberService memberService) {
            log.info("[thisExpression] memberService={}", memberService.getClass());
            assertThat(AopUtils.isAopProxy(memberService)).isTrue();
        }

        @Before("runningMembers() && target(obj)")
        void targetExpression(final MemberService memberService) {
            log.info("[targetExpression] memberService={}", memberService.getClass());
            assertThat(AopUtils.isAopProxy(memberService)).isFalse();
        }
    }

}
