package dev.jihogrammer.items.domain;

public interface Items {
    Item save(Item item);
    Item findById(ItemId itemId);
    Iterable<Item> findAll();
    Item update(ItemUpdateSource source);
}
