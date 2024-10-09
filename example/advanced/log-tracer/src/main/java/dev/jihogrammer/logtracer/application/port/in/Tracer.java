package dev.jihogrammer.logtracer.application.port.in;

import dev.jihogrammer.logtracer.domain.TraceStatus;

public interface Tracer {

    TraceStatus start(String message);

    void end(TraceStatus status);

    void fail(TraceStatus status, Exception cause);

}
