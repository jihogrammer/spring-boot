package dev.jihogrammer.common.utils;

import java.util.concurrent.atomic.AtomicLong;

public final class LongValueSequenceGenerator {
    private final AtomicLong sequence;

    public LongValueSequenceGenerator() {
        this.sequence = new AtomicLong();
    }

    public long next() {
        return sequence.addAndGet(1);
    }
}
