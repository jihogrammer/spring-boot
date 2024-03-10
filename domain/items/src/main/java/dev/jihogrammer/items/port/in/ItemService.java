package dev.jihogrammer.items.port.in;

import dev.jihogrammer.items.Item;
import dev.jihogrammer.items.model.ItemId;
import dev.jihogrammer.items.port.out.Items;

import java.util.Collection;
import java.util.NoSuchElementException;

public class ItemService implements ItemRegisterUsage, ItemReadUsage, ItemUpdateUsage {

    private final Items items;

    public ItemService(final Items items) {
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

    @Override
    public Item findById(final Long id) {
        return this.items.findById(new ItemId(id))
                .orElseThrow(() -> new NoSuchElementException("Could not found the item."));
    }

    @Override
    public Collection<Item> findAll() {
        return this.items.findAll();
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
