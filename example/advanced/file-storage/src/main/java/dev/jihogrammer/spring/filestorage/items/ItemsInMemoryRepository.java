package dev.jihogrammer.spring.filestorage.items;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

class ItemsInMemoryRepository implements Items {

    private final Map<Long, Item> items = new ConcurrentHashMap<>();
    private final AtomicLong identifier = new AtomicLong(0);

    @Override
    public Item save(final Item item) {
        item.setId(this.identifier.addAndGet(1));
        this.items.put(item.getId(), item);
        return item;
    }

    @Override
    public Item findById(final Long id) {
        return this.items.get(id);
    }
}
