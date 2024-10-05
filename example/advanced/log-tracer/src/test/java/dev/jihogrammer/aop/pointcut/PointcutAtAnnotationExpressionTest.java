package dev.jihogrammer.aop.pointcut;

import dev.jihogrammer.aop.member.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.junit.jupiter.api.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Import(PointcutAtAnnotationExpressionTest.AtAnnotationAspect.class)
@Slf4j
class PointcutAtAnnotationExpressionTest {

    @Test
    void checkBeans(
            @Autowired final MemberService memberService,
            @Autowired final AtAnnotationAspect aspect
    ) {
        log.info("memberService={}", memberService.getClass());
        assertThat(memberService).isNotNull();
        assertThat(AopUtils.isAopProxy(memberService)).isTrue();

        log.info("aspect={}", aspect.getClass());
        assertThat(aspect).isNotNull();
    }

    @Test
    void hello(@Autowired final MemberService memberService) {
        memberService.hello("jihogrammer");
    }

    @Slf4j
    @Aspect
    static class AtAnnotationAspect {
        @Around("@annotation(dev.jihogrammer.aop.member.annotation.MethodAop)")
        Object atAnnotation(final ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@annotation] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }

        // 잘 쓰지 않아서 행색만 갖춤
        @Around("execution(* dev.jihogrammer..*(..) && @args(dev.jihogrammer.aop.member.annotation.ArgsAop)")
        Object atArgs(final ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[@args] {}", joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

}
