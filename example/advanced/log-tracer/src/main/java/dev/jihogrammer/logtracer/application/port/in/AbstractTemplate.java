package dev.jihogrammer.logtracer.application.port.in;

import dev.jihogrammer.logtracer.domain.TraceStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class AbstractTemplate<T> {

    private final Tracer tracer;

    public T execute(final String message) {
        TraceStatus status = null;

        try {
            status = this.tracer.start(message);

            T result = this.call();

            this.tracer.end(status);
            return result;
        } catch (Exception e) {
            this.tracer.fail(status, e);
            throw e;
        }
    }

    protected abstract T call();

}
