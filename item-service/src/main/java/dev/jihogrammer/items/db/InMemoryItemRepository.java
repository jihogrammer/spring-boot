package dev.jihogrammer.items.db;

import dev.jihogrammer.items.domain.Item;
import dev.jihogrammer.items.domain.ItemId;
import dev.jihogrammer.items.domain.ItemUpdateSource;
import dev.jihogrammer.items.domain.Items;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryItemRepository implements Items {
    private final Map<ItemId, Item> store;
    private final AtomicLong sequence;

    public InMemoryItemRepository(final Map<ItemId, Item> store, final AtomicLong atomicLong) {
        this.store = store;
        this.sequence = atomicLong;
    }

    @Override
    public Item save(final Item item) {
        item.setId(new ItemId(sequence.addAndGet(1)));
        store.put(item.getId(), item);
        return item;
    }

    @Override
    public Item findById(final ItemId itemId) {
        return store.get(itemId);
    }

    @Override
    public Iterable<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Item update(final ItemUpdateSource source) {
        Item item = store.get(source.itemId());

        item.setName(source.name());
        item.setPrice(source.price());
        item.setQuantity(source.quantity());

        return item;
    }

    public void clearStore() {
        store.clear();
    }
}
