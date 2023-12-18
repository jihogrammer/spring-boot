package dev.jihogrammer.items.model.out;

import dev.jihogrammer.items.domain.model.Item;

import java.util.Collection;

public final class ItemViewMapper {
    public static ItemView mapToView(final Item item) {
        return new ItemView(
            item.id().value(),
            item.name().value(),
            item.price().value(),
            item.quantity().value(),
            item.open(),
            item.regions(),
            item.itemType(),
            item.deliveryCode());
    }

    public static Collection<ItemView> mapToView(final Collection<Item> items) {
        return items.stream().map(ItemViewMapper::mapToView).toList();
    }
}
