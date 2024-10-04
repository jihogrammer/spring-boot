package dev.jihogrammer.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
@SuppressWarnings("unused")
public class V1Aspect {

    @Around("execution(* dev.jihogrammer.aop..*(..))")
    Object applyLogging(final ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[{}] {}", this.getClass().getSimpleName(), joinPoint.getSignature());
        return joinPoint.proceed();
    }

}
