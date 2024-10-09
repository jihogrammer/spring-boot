package dev.jihogrammer.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;

@Slf4j
public class V5Aspect {

    @Aspect
    @Order(2)
    @SuppressWarnings("unused")
    public static class SecondaryAspect {
        @Around("dev.jihogrammer.aop.aspect.Pointcuts.allOrder()")
        Object applyLogging(final ProceedingJoinPoint joinPoint) throws Throwable {
            log.info("[{}] {}", this.getClass().getSimpleName(), joinPoint.getSignature());
            return joinPoint.proceed();
        }
    }

    @Aspect
    @Order(1)
    @SuppressWarnings("unused")
    public static class PrimaryAspect {
        @Around("dev.jihogrammer.aop.aspect.Pointcuts.allOrderService()")
        Object applyTransaction(final ProceedingJoinPoint joinPoint) throws Throwable {
            try {
                // @Before
                log.info("[Transaction start]");

                var result = joinPoint.proceed();

                // @AfterReturning
                log.info("[Transaction commit]");

                return result;
            } catch (Exception e) {
                // @AfterThrowing
                log.info("[Transaction rollback]");
                throw e;
            } finally {
                // @After
                log.info("[Transaction release]");
            }
        }
    }

}
