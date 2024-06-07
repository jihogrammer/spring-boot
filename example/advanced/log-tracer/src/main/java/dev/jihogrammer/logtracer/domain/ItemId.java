package dev.jihogrammer.logtracer.domain;

import dev.jihogrammer.logtracer.domain.exception.ItemException;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class ItemId {

    private final String value;

    public ItemId(final String value) {
        if (value == null || value.isBlank()) {
            throw new ItemException("ItemId value is null.");
        }

        this.value = value;
    }

    public static ItemId of(final String value) {
        if (ExceptionItemId.VALUE.equalsIgnoreCase(value)) {
            return new ExceptionItemId();
        }
        return new ItemId(value);
    }

    public String value() {
        return this.value;
    }

    public static final class ExceptionItemId extends ItemId {

        public static final String VALUE = "EXCEPTION";

        public ExceptionItemId() {
            super(VALUE);
        }

    }

}
