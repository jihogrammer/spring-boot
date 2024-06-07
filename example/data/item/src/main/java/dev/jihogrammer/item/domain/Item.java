package dev.jihogrammer.item.domain;

import dev.jihogrammer.item.domain.exception.ItemException;

public record Item(
        ItemId id,
        String name,
        Integer price,
        Integer quantity
) {

    public Item {
        if (id == null) {
            throw new ItemException("ItemId is null.");
        }
        if (name == null || name.isBlank()) {
            throw new ItemException("Item name is blank.");
        }
        if (price == null || price < 0) {
            throw new ItemException("Item price is not valid. price=" + price);
        }
        if (quantity == null || quantity < 0) {
            throw new ItemException("Item quantity is not valid. quantity=" + quantity);
        }
    }

}
