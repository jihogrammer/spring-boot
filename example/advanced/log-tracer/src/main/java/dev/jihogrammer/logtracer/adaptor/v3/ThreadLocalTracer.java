package dev.jihogrammer.logtracer.adaptor.v3;

import dev.jihogrammer.logtracer.application.port.in.Tracer;
import dev.jihogrammer.logtracer.domain.TraceId;
import dev.jihogrammer.logtracer.domain.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ThreadLocalTracer implements Tracer {

    private static final String START_PREFIX = "-->";

    private static final String COMPLETE_PREFIX = "<--";

    private static final String EXCEPTION_PREFIX = "<X-";

    /**
     * Thread safety TraceIdHolder.
     */
    private final ThreadLocal<TraceId> traceIdHolder = ThreadLocal.withInitial(() -> null);

    @Override
    public TraceStatus start(final String message) {
        final var traceId = this.syncTraceId();
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

    private TraceId syncTraceId() {
        final var traceId = this.traceIdHolder.get();

        if (traceId == null) {
            this.traceIdHolder.set(new TraceId());
        } else {
            this.traceIdHolder.set(traceId.next());
        }

        return this.traceIdHolder.get();
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

        this.releaseTraceId();
    }

    private void releaseTraceId() {
        final var traceId = this.traceIdHolder.get();

        if (traceId.isFirstLevel()) {
            this.traceIdHolder.remove();
        } else {
            this.traceIdHolder.set(traceId.prev());
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
