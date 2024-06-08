package dev.jihogrammer.logtracer.application.service.trace;

import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.domain.TraceId;
import dev.jihogrammer.logtracer.domain.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class V1TraceService implements Tracer {

    private static final String START_PREFIX = "-->";

    private static final String COMPLETE_PREFIX = "<--";

    private static final String EXCEPTION_PREFIX = "<X-";

    @Override
    public TraceStatus start(final String message) {
        final var traceId = new TraceId();
        final var startTime = System.currentTimeMillis();

        log.info("[{}] {}{}", traceId.value(), this.drawArrow(START_PREFIX, traceId.level()), message);

        return new TraceStatus(traceId, startTime, message);
    }

    @Override
    public void end(final TraceStatus status) {
        this.complete(status, null);
    }

    @Override
    public void fail(final TraceStatus status, final Exception cause) {
        this.complete(status, cause);
    }

    private void complete(final TraceStatus status, final Exception cause) {
        final var traceId = status.traceId();
        final var elapsed = System.currentTimeMillis() - status.startTime();

        if (cause == null) {
            log.info("[{}] {}{} elapsed=[{} ms]",
                    traceId.value(), this.drawArrow(COMPLETE_PREFIX, traceId.level()), status.message(), elapsed);
        } else {
            log.info("[{}] {}{} elapsed=[{} ms]; cause=[{}; {}]",
                    traceId.value(), this.drawArrow(EXCEPTION_PREFIX, traceId.level()), status.message(), elapsed,
                    cause.getClass(), cause.getMessage());
        }
    }

    private String drawArrow(final String prefix, final Integer level) {
        final var sb = new StringBuilder();

        for (var i = 0; i < level; i++) {
            sb.append((i + 1 == level) ? ("|" + prefix) : "|   ");
        }

        return sb.toString();
    }

}
