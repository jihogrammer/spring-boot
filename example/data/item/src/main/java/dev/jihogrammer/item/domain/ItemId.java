package dev.jihogrammer.item.domain;

import dev.jihogrammer.item.domain.exception.ItemException;

public record ItemId(String value) {

    public ItemId {
        if (value == null || value.isBlank()) {
            throw new ItemException("ItemId value is blank.");
        }
    }

}
