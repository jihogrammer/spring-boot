package dev.jihogrammer.logtracer.domain;

public record TraceStatus(TraceId traceId, Long startTime, String message) {
}
