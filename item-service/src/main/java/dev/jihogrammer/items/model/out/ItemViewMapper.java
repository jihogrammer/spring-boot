package dev.jihogrammer.items.model.out;

import dev.jihogrammer.items.domain.model.Item;

import java.util.Collection;

public final class ItemViewMapper {
    public static ItemView mapToView(final Item item) {
        ItemView itemView = new ItemView();
        itemView.setId(item.id().value());
        itemView.setName(item.name().value());
        itemView.setPrice(item.price().value());
        itemView.setQuantity(item.quantity().value());
        itemView.setOpen(item.open());
        itemView.setRegions(item.regions());
        return itemView;
    }

    public static Collection<ItemView> mapToView(final Collection<Item> items) {
        return items.stream().map(ItemViewMapper::mapToView).toList();
    }
}
