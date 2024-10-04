package dev.jihogrammer.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
@SuppressWarnings("unused")
public class V4Aspect {

    @Around("dev.jihogrammer.aop.aspect.Pointcuts.allOrder()")
    Object applyLogging(final ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("[{}] {}", this.getClass().getSimpleName(), joinPoint.getSignature());
        return joinPoint.proceed();
    }

    @Around("dev.jihogrammer.aop.aspect.Pointcuts.allOrderService()")
    Object applyTransaction(final ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            log.info("[Transaction start]");

            var result = joinPoint.proceed();

            log.info("[Transaction commit]");

            return result;
        } catch (Exception e) {
            log.info("[Transaction rollback]");
            throw e;
        } finally {
            log.info("[Transaction release]");
        }
    }

}
