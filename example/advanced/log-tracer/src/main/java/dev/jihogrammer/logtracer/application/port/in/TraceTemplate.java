package dev.jihogrammer.logtracer.application.port.in;

import dev.jihogrammer.logtracer.domain.TraceStatus;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TraceTemplate {

    private final Tracer tracer;

    public <T> T execute(final String message, final TraceCallback<T> callback) {
        TraceStatus status = null;

        try {
            status = this.tracer.start(message);

            T result = callback.call();

            this.tracer.end(status);
            return result;
        } catch (Exception e) {
            this.tracer.fail(status, e);
            throw e;
        }
    }

}
