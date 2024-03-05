package dev.jihogrammer.items.model.vo;

public record ItemName(String value) {
    public ItemName {
        if (value == null) {
            throw new NullPointerException("name must not be null");
        }
        if (value.isEmpty()) {
            throw new IllegalArgumentException("name must not empty");
        }
    }
}
