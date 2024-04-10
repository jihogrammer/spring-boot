package dev.jihogrammer.product.port.in;

import dev.jihogrammer.product.Item;
import dev.jihogrammer.product.domain.model.ItemId;
import dev.jihogrammer.product.domain.model.ItemUpdateCommand;
import dev.jihogrammer.product.port.out.Items;

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
