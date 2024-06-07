package dev.jihogrammer.item.domain;

import dev.jihogrammer.item.domain.exception.ItemException;

public record ItemId(Long value) {

    public ItemId {
        if (value == null) {
            throw new ItemException("ItemId value is null.");
        }
    }

}
