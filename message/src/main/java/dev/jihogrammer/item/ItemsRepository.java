package dev.jihogrammer.item;

import dev.jihogrammer.items.Items;
import dev.jihogrammer.items.Item;
import dev.jihogrammer.items.model.in.ItemSaveCommand;
import dev.jihogrammer.items.model.in.ItemUpdateCommand;
import dev.jihogrammer.items.model.vo.ItemId;
import dev.jihogrammer.items.model.vo.ItemName;
import dev.jihogrammer.items.model.vo.ItemPrice;
import dev.jihogrammer.items.model.vo.ItemQuantity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ItemsRepository implements Items {
    private final Map<ItemId, Item> store;
    private final AtomicLong sequence;

    public ItemsRepository() {
        this.store = new ConcurrentHashMap<>();
        this.sequence = new AtomicLong();
    }

    @Override
    public Item save(final ItemSaveCommand command) {
        ItemId itemId = generateItemId();
        Item item = mapToItem(itemId, command);
        this.store.put(itemId, item);
        return this.store.get(itemId);
    }

    @Override
    public Item findById(final ItemId itemId) {
        return this.store.get(itemId);
    }

    @Override
    public Collection<Item> findAll() {
        return this.store.values();
    }

    @Override
    public Item update(final ItemUpdateCommand command) {
        ItemId itemId = new ItemId(command.getId());
        Item item = mapToItem(itemId, command);
        this.store.put(itemId, item);
        return this.store.get(itemId);
    }

    private static Item mapToItem(final ItemId itemId, final ItemSaveCommand command) {
        return new Item(
            itemId,
            new ItemName(command.getName()),
            new ItemPrice(command.getPrice()),
            new ItemQuantity(command.getQuantity()),
            null,
            null,
            null,
            null);
    }

    private static Item mapToItem(final ItemId itemId, final ItemUpdateCommand command) {
        return new Item(
            itemId,
            new ItemName(command.getName()),
            new ItemPrice(command.getPrice()),
            new ItemQuantity(command.getQuantity()),
            null,
            null,
            null,
            null);
    }

    private ItemId generateItemId() {
        return new ItemId(this.sequence.addAndGet(1));
    }
}
