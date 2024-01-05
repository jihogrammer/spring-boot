package dev.jihogrammer.items.model.vo;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class ItemQuantity {
    private final int value;

    public ItemQuantity(final Integer value) {
        if (value == null) {
            throw new NullPointerException("quantity must not be null");
        }
        if (value < 0) {
            throw new IllegalArgumentException("quantity must be positive value");
        }
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
