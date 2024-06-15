package dev.jihogrammer.logtracer.adaptor.proxy.v6;

import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.domain.TraceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
class LoggingProxyHandler implements InvocationHandler {

    private final Object target;

    private final Tracer tracer;

    private final String[] pattern;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final var methodName = method.getName();
        if (!PatternMatchUtils.simpleMatch(this.pattern, methodName)) {
            return method.invoke(this.target, args);
        }

        TraceStatus status = null;
        try {
            status = this.tracer.start("%s.%s(%s)".formatted(
                    method.getDeclaringClass().getSimpleName(),
                    methodName,
                    Arrays.toString(args)));
            var result = method.invoke(this.target, args);
            this.tracer.end(status);
            return result;
        } catch (Exception e) {
            this.tracer.fail(status, e);
            throw e;
        }
    }

}
