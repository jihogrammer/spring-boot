package dev.jihogrammer.items.model.vo;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public final class ItemPrice {
    private final int value;

    public ItemPrice(final Integer value) {
        if (value == null) {
            throw new NullPointerException("price must not be null");
        }
        if (value < 0) {
            throw new IllegalArgumentException("price must be positive value");
        }
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
