package dev.jihogrammer.logtracer.application.port.in;

import dev.jihogrammer.logtracer.domain.TraceId;
import dev.jihogrammer.logtracer.domain.TraceStatus;

public interface SyncTracer extends Tracer {

    TraceStatus start(TraceId prevTraceId, String message);

}
