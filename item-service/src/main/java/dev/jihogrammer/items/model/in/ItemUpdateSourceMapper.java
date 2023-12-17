package dev.jihogrammer.items.model.in;

import dev.jihogrammer.items.domain.model.Item;
import dev.jihogrammer.items.domain.model.ItemUpdateCommand;
import dev.jihogrammer.items.vo.DeliveryCode;

public final class ItemUpdateSourceMapper {
    public static ItemUpdateSource mapToUpdateSource(final Item item) {
        return new ItemUpdateSource(
            item.id().value(),
            item.name().value(),
            item.price().value(),
            item.quantity().value(),
            item.open(),
            item.regions(),
            item.itemType(),
            item.deliveryCode());
    }

    public static ItemUpdateCommand mapToUpdateCommand(final ItemUpdateSource itemUpdateSource) {
        return new ItemUpdateCommand(
            itemUpdateSource.getId(),
            itemUpdateSource.getName(),
            itemUpdateSource.getPrice(),
            itemUpdateSource.getQuantity(),
            itemUpdateSource.getOpen(),
            itemUpdateSource.getRegions(),
            itemUpdateSource.getItemType(),
            itemUpdateSource.getDeliveryCode());
    }
}
