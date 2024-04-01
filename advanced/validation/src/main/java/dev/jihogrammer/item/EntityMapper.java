package dev.jihogrammer.item;

import dev.jihogrammer.item.model.out.ItemView;
import dev.jihogrammer.items.model.Item;

import java.util.Collection;

public final class EntityMapper {

    public static ItemView map(final Item item) {
        return new ItemView(
                item.id().value(),
                item.name(),
                item.price(),
                item.quantity());
    }

    public static Collection<ItemView> map(final Collection<Item> items) {
        return items.stream().map(EntityMapper::map).toList();
    }

}
