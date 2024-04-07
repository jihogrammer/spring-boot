package dev.jihogrammer.items.port.in;

import dev.jihogrammer.items.Item;
import dev.jihogrammer.items.model.ItemRegisterCommand;
import dev.jihogrammer.items.port.out.Items;

public class ItemRegisterInteractor implements ItemRegisterUsage {

    private final Items items;

    public ItemRegisterInteractor(final Items items) {
        this.items = items;
    }

    @Override
    public Item register(final ItemRegisterCommand command) {
        var item = new Item(
                this.items.nextId(),
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
