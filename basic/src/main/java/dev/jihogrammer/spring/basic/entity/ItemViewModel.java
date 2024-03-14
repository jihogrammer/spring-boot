package dev.jihogrammer.spring.basic.entity;

import dev.jihogrammer.items.model.Item;
import dev.jihogrammer.items.model.ItemType;

import java.util.Collection;
import java.util.Set;

public record ItemViewModel(
        Long id,
        String name,
        Integer price,
        Integer quantity,
        Boolean open,
        Set<String> regions,
        ItemType itemType,
        String deliveryCode
) {

    public static ItemViewModel of(final Item item) {
        return new ItemViewModel(
                item.id().value(),
                item.name(),
                item.price(),
                item.quantity(),
                item.open(),
                item.regions(),
                item.itemType(),
                item.deliveryCode());
    }

    public static Collection<ItemViewModel> of(final Collection<Item> items) {
        return items.stream().map(ItemViewModel::of).toList();
    }

}
