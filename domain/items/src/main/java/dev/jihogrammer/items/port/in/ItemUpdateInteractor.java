package dev.jihogrammer.items.port.in;

import dev.jihogrammer.items.Item;
import dev.jihogrammer.items.model.ItemId;
import dev.jihogrammer.items.model.ItemUpdateCommand;
import dev.jihogrammer.items.port.out.Items;

public class ItemUpdateInteractor implements ItemUpdateUsage {

    private final Items items;

    public ItemUpdateInteractor(final Items items) {
        this.items = items;
    }

    @Override
    public Item update(final ItemUpdateCommand command) {
        var item = new Item(
                new ItemId(command.id()),
                command.name(),
                command.price(),
                command.quantity(),
                command.open(),
                command.regions(),
                command.itemType(),
                command.deliveryCode());

        return this.items.save(item);
    }

}
