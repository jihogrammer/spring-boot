package dev.jihogrammer.items.port.out;

import dev.jihogrammer.items.Item;
import dev.jihogrammer.items.model.vo.ItemId;

import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryItemRepository implements Items {

    private final Map<ItemId, Item> store = new ConcurrentHashMap<>();

    @Override
    public Item save(final ItemRegisterCommand command) {
        var item = Item.of(ItemIdGenerator.next(), command);

        this.store.put(item.id(), item);

        return item;
    }

    @Override
    public Item findById(final ItemId itemId) {
        return Optional.ofNullable(this.store.get(itemId))
            .orElseThrow(() -> new NoSuchElementException("Could not found the item."));
    }

    @Override
    public Collection<Item> findAll() {
        return store.values().stream().toList();
    }

    @Override
    public Item update(final ItemUpdateCommand command) {
        var item = this.findById(new ItemId(command.id()));
        var updatedItem = Item.of(command);

        this.store.put(item.id(), updatedItem);

        return updatedItem;
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
