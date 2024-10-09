package dev.jihogrammer.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;

@Slf4j
@Aspect
public class V6Aspect {

    @Before("dev.jihogrammer.aop.aspect.Pointcuts.allOrderService()")
    void before(final JoinPoint joinPoint) {
        log.info("[@Before] {}", joinPoint.getSignature());
    }

    @AfterReturning(value = "dev.jihogrammer.aop.aspect.Pointcuts.allOrderService()", returning = "result")
    void afterReturning(final JoinPoint joinPoint, final String result) {
        log.info("[@AfterReturning] {}; result={};", joinPoint.getSignature(), result);
    }

    @AfterThrowing(value = "dev.jihogrammer.aop.aspect.Pointcuts.allOrderService()", throwing = "exception")
    void afterThrowing(final JoinPoint joinPoint, final Exception exception) {
        log.info("[@AfterThrowing] {}; exception={};", joinPoint.getSignature(), exception.getMessage());
    }

    @After("dev.jihogrammer.aop.aspect.Pointcuts.allOrderService()")
    void after(final JoinPoint joinPoint) {
        log.info("[@After] {}", joinPoint.getSignature());
    }

}
