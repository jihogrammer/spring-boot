package dev.jihogrammer.product.port.out;

import dev.jihogrammer.product.Item;
import dev.jihogrammer.product.domain.model.ItemId;
import dev.jihogrammer.product.domain.model.ItemRegisterCommand;

import java.util.Collection;
import java.util.Optional;

public interface Items {

    ItemId nextId();

    Item save(Item item);

    Item save(ItemRegisterCommand item);

    Optional<Item> findById(ItemId itemId);

    Collection<Item> findAll();

}
