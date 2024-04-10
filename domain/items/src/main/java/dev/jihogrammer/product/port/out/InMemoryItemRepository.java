package dev.jihogrammer.product.port.out;

import dev.jihogrammer.product.Item;
import dev.jihogrammer.product.domain.model.ItemId;
import dev.jihogrammer.product.domain.model.ItemRegisterCommand;

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
    public Item save(final ItemRegisterCommand command) {
        var item = new Item(
            ItemIdGenerator.next(),
            command.name(),
            command.price(),
            command.quantity(),
            command.open(),
            command.regions(),
            command.itemType(),
            command.deliveryCode());

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

        private static final AtomicLong SEQUENCE = new AtomicLong();

        public static ItemId next() {
            return new ItemId(SEQUENCE.incrementAndGet());
        }

    }

}
