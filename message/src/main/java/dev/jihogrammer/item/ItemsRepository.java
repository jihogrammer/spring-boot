package dev.jihogrammer.item;

import dev.jihogrammer.common.utils.RandomUtils;
import dev.jihogrammer.items.domain.Items;
import dev.jihogrammer.items.domain.model.Item;
import dev.jihogrammer.items.domain.model.ItemSaveCommand;
import dev.jihogrammer.items.domain.model.ItemUpdateCommand;
import dev.jihogrammer.items.vo.ItemId;
import dev.jihogrammer.items.vo.ItemName;
import dev.jihogrammer.items.vo.ItemPrice;
import dev.jihogrammer.items.vo.ItemQuantity;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
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
        if (this.store.isEmpty()) {
            return List.of(generateRandomItem(), generateRandomItem(), generateRandomItem());
        }
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

    private Item generateRandomItem() {
        ItemId itemId  = new ItemId(RandomUtils.randomLong());

        while (this.store.containsKey(itemId)) {
            itemId = new ItemId(RandomUtils.randomLong());
        }

        return new Item(
            itemId,
            new ItemName(RandomUtils.randomString()),
            new ItemPrice(RandomUtils.randomInt()),
            new ItemQuantity(RandomUtils.randomInt()),
            null,
            null,
            null,
            null);
    }
}
