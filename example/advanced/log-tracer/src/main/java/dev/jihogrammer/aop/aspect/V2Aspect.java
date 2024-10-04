package dev.jihogrammer.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
@SuppressWarnings("unused")
public class V2Aspect {

    @Pointcut("execution(* dev.jihogrammer.aop..*(..))")
    private void allOrder() {
    }

    @Around("allOrder()")
    Object applyLogging(final ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[{}] {}", this.getClass().getSimpleName(), joinPoint.getSignature());
        return joinPoint.proceed();
    }

}
