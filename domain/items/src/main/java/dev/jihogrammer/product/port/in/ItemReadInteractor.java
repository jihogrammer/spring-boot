package dev.jihogrammer.product.port.in;

import dev.jihogrammer.product.Item;
import dev.jihogrammer.product.domain.exception.ItemException;
import dev.jihogrammer.product.domain.model.ItemId;
import dev.jihogrammer.product.port.out.Items;

import java.util.Collection;

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
