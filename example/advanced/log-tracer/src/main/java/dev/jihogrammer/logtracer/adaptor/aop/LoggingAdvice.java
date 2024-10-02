package dev.jihogrammer.logtracer.adaptor.aop;

import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.domain.TraceStatus;
import lombok.RequiredArgsConstructor;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.lang.NonNull;

import java.util.Arrays;

@RequiredArgsConstructor
public class LoggingAdvice implements MethodInterceptor {

    private final Tracer tracer;

    @Override
    public Object invoke(@NonNull final MethodInvocation invocation) throws Throwable {
        TraceStatus status = null;
        try {
            status = this.tracer.start("%s.%s(%s)".formatted(
                    invocation.getMethod().getDeclaringClass().getSimpleName(),
                    invocation.getMethod().getName(),
                    Arrays.toString(invocation.getArguments())));

            final var result = invocation.proceed();
            this.tracer.end(status);

            return result;
        } catch (Exception e) {
            this.tracer.fail(status, e);
            throw e;
        }
    }

}
