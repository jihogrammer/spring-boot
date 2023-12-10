package dev.jihogrammer.common.model;

public interface SingleValueObject<T> {
    T value();

    default boolean isEmpty() {
        return value() == null;
    }

    default boolean isNotEmpty() {
        return !isEmpty();
    }
}
