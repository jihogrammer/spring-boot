package dev.jihogrammer.items.port.out;

import dev.jihogrammer.items.model.Item;
import dev.jihogrammer.items.model.ItemId;

import java.util.Collection;
import java.util.Optional;

public interface Items {

    ItemId nextId();

    Item save(Item item);

    Optional<Item> findById(ItemId itemId);

    Collection<Item> findAll();

}
