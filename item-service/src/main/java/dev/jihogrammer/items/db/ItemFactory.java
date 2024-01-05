package dev.jihogrammer.items.db;

import dev.jihogrammer.items.Item;
import dev.jihogrammer.items.model.in.ItemSaveCommand;
import dev.jihogrammer.items.model.in.ItemUpdateCommand;
import dev.jihogrammer.items.model.vo.ItemId;
import dev.jihogrammer.items.model.vo.ItemName;
import dev.jihogrammer.items.model.vo.ItemPrice;
import dev.jihogrammer.items.model.vo.ItemQuantity;

public final class ItemFactory {
    public static Item makeFromSaveCommand(final Long id, final ItemSaveCommand command) {
        return new Item(
            new ItemId(id),
            new ItemName(command.getName()),
            new ItemPrice(command.getPrice()),
            new ItemQuantity(command.getQuantity()),
            command.getOpen(),
            command.getRegions(),
            command.getItemType(),
            command.getDeliveryCode());
    }

    public static Item makeFromUpdateCommand(final ItemUpdateCommand command) {
        return new Item(
            new ItemId(command.getId()),
            new ItemName(command.getName()),
            new ItemPrice(command.getPrice()),
            new ItemQuantity(command.getQuantity()),
            command.getOpen(),
            command.getRegions(),
            command.getItemType(),
            command.getDeliveryCode());
    }
}
