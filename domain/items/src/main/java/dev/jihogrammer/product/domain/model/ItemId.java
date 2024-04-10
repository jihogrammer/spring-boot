package dev.jihogrammer.product.domain.model;

public record ItemId(long value) {

    public ItemId {
        if (value < 0) {
            throw new IllegalAccessError("ID must be positive value");
        }
    }

}
