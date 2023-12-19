package dev.jihogrammer.items.db;

import dev.jihogrammer.items.Items;
import dev.jihogrammer.items.Item;
import dev.jihogrammer.items.model.in.ItemSaveCommand;
import dev.jihogrammer.items.model.in.ItemUpdateCommand;
import dev.jihogrammer.items.model.vo.ItemId;

import java.util.Collection;
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
    public Item save(final ItemSaveCommand command) {
        Item item = ItemFactory.makeFromSaveCommand(sequence.addAndGet(1), command);
        store.put(item.id(), item);
        return item;
    }

    @Override
    public Item findById(final ItemId itemId) {
        return store.get(itemId);
    }

    @Override
    public Collection<Item> findAll() {
        return store.values().stream().toList();
    }

    @Override
    public Item update(final ItemUpdateCommand command) {
        ItemId itemId = new ItemId(command.getId());
        store.put(itemId, ItemFactory.makeFromUpdateCommand(command));
        return store.get(itemId);
    }

    public void clearStore() {
        store.clear();
    }
}
