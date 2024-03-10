package dev.jihogrammer.items.port.out;

import dev.jihogrammer.items.Item;
import dev.jihogrammer.items.model.ItemId;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryItemRepository implements Items {

    private final Map<ItemId, Item> store = new ConcurrentHashMap<>();

    @Override
    public ItemId nextId() {
        return ItemIdGenerator.next();
    }

    @Override
    public Item save(final Item item) {
        this.store.put(item.id(), item);

        return item;
    }

    @Override
    public Optional<Item> findById(final ItemId itemId) {
        return Optional.ofNullable(this.store.get(itemId));
    }

    @Override
    public Collection<Item> findAll() {
        return this.store.values().stream().toList();
    }

    public void clear() {
        this.store.clear();
    }

    private static class ItemIdGenerator {

        private static final AtomicLong SEQUENCE;

        static {
            SEQUENCE = new AtomicLong();
        }

        public static ItemId next() {
            return new ItemId(SEQUENCE.addAndGet(1));
        }

    }

}
