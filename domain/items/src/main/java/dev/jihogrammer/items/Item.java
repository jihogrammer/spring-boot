package dev.jihogrammer.items;

import dev.jihogrammer.items.model.ItemId;
import dev.jihogrammer.items.model.ItemType;

import java.util.Set;

import static java.util.Objects.isNull;

public record Item(
    ItemId id,
    String name,
    Integer price,
    Integer quantity,
    Boolean open,
    Set<String> regions,
    ItemType itemType,
    String deliveryCode
) {

    public Item {
        if (isNull(id)) {
            throw new IllegalArgumentException("item id is null");
        }
        if (isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException("item name is blank");
        }
    }

}
