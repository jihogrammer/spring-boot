package dev.jihogrammer.logtracer.adaptor.aop;

import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.domain.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@RequiredArgsConstructor
public class LoggingTraceAspect {

    private final Tracer tracer;

    @Around("""
            execution(* dev.jihogrammer.logtracer.adaptor.app..*(..))
            && !execution(* dev.jihogrammer.logtracer.adaptor.app..noLog(..))""")
    Object applyLogging(final ProceedingJoinPoint joinPoint) throws Throwable {
        TraceStatus status = null;
        try {
            status = this.tracer.start(joinPoint.getSignature().toShortString());

            final var result = joinPoint.proceed();
            this.tracer.end(status);

            return result;
        } catch (Throwable e) {
            this.tracer.fail(status, new Exception(e));
            throw e;
        }
    }

}
