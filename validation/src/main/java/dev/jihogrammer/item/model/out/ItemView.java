package dev.jihogrammer.item.model.out;

import dev.jihogrammer.items.Item;

public record ItemView(long id, String name, int price, int quantity) {
    public static ItemView of(final Item item) {
        return new ItemView(
            item.id().value(),
            item.name(),
            item.price(),
            item.quantity());
    }
}
