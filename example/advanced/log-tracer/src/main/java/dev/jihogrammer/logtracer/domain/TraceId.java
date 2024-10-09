package dev.jihogrammer.logtracer.domain;

import lombok.ToString;

import java.util.UUID;

@ToString
public final class TraceId {

    private final String value;

    private final Integer level;

    public TraceId() {
        this.value = this.generateValue();
        this.level = 0;
    }

    private TraceId(final String value, final Integer level) {
        this.value = value;
        this.level = level;
    }

    public TraceId next() {
        return new TraceId(this.value, this.level + 1);
    }

    public TraceId prev() {
        return new TraceId(this.value, this.level - 1);
    }

    public boolean isFirstLevel() {
        return this.level == 0;
    }

    public String value() {
        return this.value;
    }

    public Integer level() {
        return this.level;
    }

    private String generateValue() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

}
