package dev.jihogrammer.items.port.out;

import dev.jihogrammer.items.Item;
import dev.jihogrammer.items.model.ItemId;
import dev.jihogrammer.items.model.ItemRegisterCommand;

import java.util.Collection;
import java.util.Optional;

public interface Items {

    ItemId nextId();

    Item save(Item item);

    Item save(ItemRegisterCommand item);

    Optional<Item> findById(ItemId itemId);

    Collection<Item> findAll();

}
