package dev.jihogrammer.items.port.in;

import dev.jihogrammer.items.exception.ItemException;
import dev.jihogrammer.items.model.Item;
import dev.jihogrammer.items.model.ItemId;
import dev.jihogrammer.items.model.ItemRegisterCommand;
import dev.jihogrammer.items.model.ItemUpdateCommand;
import dev.jihogrammer.items.port.out.Items;

import java.util.Collection;
import java.util.NoSuchElementException;

public class ItemReadInteractor implements ItemReadUsage {

    private final Items items;

    public ItemReadInteractor(final Items items) {
        this.items = items;
    }

    @Override
    public Item findById(final Long id) throws ItemException {
        return this.items.findById(new ItemId(id))
                .orElseThrow(() -> new ItemException("Could not found the item."));
    }

    @Override
    public Collection<Item> findAll() {
        return this.items.findAll();
    }

}
