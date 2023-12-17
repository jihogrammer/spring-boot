package dev.jihogrammer.items.model.in;

import dev.jihogrammer.items.domain.model.Item;
import dev.jihogrammer.items.domain.model.ItemUpdateCommand;

public final class ItemUpdateSourceMapper {
    public static ItemUpdateSource mapToUpdateSource(final Item item) {
        ItemUpdateSource itemUpdateSource = new ItemUpdateSource();
        itemUpdateSource.setId(item.id().value());
        itemUpdateSource.setName(item.name().value());
        itemUpdateSource.setPrice(item.price().value());
        itemUpdateSource.setQuantity(item.quantity().value());
        itemUpdateSource.setOpen(item.open());
        return itemUpdateSource;
    }

    public static ItemUpdateCommand mapToUpdateCommand(final ItemUpdateSource itemUpdateSource) {
        return new ItemUpdateCommand(
            itemUpdateSource.getId(),
            itemUpdateSource.getName(),
            itemUpdateSource.getPrice(),
            itemUpdateSource.getQuantity());
    }
}
