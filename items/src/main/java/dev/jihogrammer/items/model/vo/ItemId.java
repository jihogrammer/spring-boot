package dev.jihogrammer.items.model.vo;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class ItemId {
    private final long value;

    public ItemId(final Long value) throws NullPointerException, IllegalArgumentException {
        if (value == null) {
            throw new NullPointerException("ID must not be null");
        }
        if (value < 0) {
            throw new IllegalAccessError("ID must be positive value");
        }
        this.value = value;
    }

    public long value() {
        return this.value;
    }
}
