package dev.jihogrammer.items.db;

import dev.jihogrammer.items.domain.model.Item;
import dev.jihogrammer.items.domain.model.ItemSaveCommand;
import dev.jihogrammer.items.domain.model.ItemUpdateCommand;
import dev.jihogrammer.items.vo.ItemId;
import dev.jihogrammer.items.vo.ItemName;
import dev.jihogrammer.items.vo.ItemPrice;
import dev.jihogrammer.items.vo.ItemQuantity;

public final class ItemFactory {
    public static Item makeFromSaveCommand(final Long id, final ItemSaveCommand command) {
        return new Item(
            new ItemId(id),
            new ItemName(command.getName()),
            new ItemPrice(command.getPrice()),
            new ItemQuantity(command.getQuantity()),
            command.getOpen(),
            null, null, null);
    }

    public static Item makeFromUpdateCommand(final ItemUpdateCommand command) {
        return new Item(
            new ItemId(command.getId()),
            new ItemName(command.getName()),
            new ItemPrice(command.getPrice()),
            new ItemQuantity(command.getQuantity()),
            null, null, null, null);
    }
}
