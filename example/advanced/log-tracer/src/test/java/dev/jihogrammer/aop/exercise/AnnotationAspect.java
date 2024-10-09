package dev.jihogrammer.aop.exercise;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Aspect
@Slf4j
class AnnotationAspect {

    @Pointcut("execution(* dev.jihogrammer.aop.exercise..*(..))")
    private void runningExercise() {
    }

    @Before("runningExercise() && @annotation(dev.jihogrammer.aop.exercise.Trace)")
    void applyTrace(final JoinPoint joinPoint) {
        log.info("signature={}; args={};", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @Around("runningExercise() && @annotation(retry)")
    Object applyRetry(final ProceedingJoinPoint joinPoint, final Retry retry) throws Throwable {
        var count = new AtomicInteger(0);
        var exceptionHolder = new ExceptionHolder(retry.value());

        while (retry.value() > count.incrementAndGet()) {
            try {
                return joinPoint.proceed();
            } catch (Exception e) {
                exceptionHolder.add(e);
                log.warn("Retry {}; Failed to proceed; signature={}; args={}", count.get(), joinPoint.getSignature(), joinPoint.getArgs());
            }
        }

        throw exceptionHolder.getLastException();
    }

    private static class ExceptionHolder {

        private final List<Exception> exceptions;

        ExceptionHolder(final int capacity) {
            this.exceptions = new ArrayList<>(capacity);
        }

        Exception getLastException() {
            return this.exceptions.get(this.exceptions.size() - 1);
        }

        void add(final Exception e) {
            this.exceptions.add(e);
        }

    }

}
