package dev.jihogrammer.items.port.out;

import dev.jihogrammer.items.Item;
import dev.jihogrammer.items.model.vo.ItemId;

import java.util.Collection;
import java.util.Optional;

public interface Items {

    Item save(ItemRegisterCommand command);

    Item findById(ItemId itemId);

    Collection<Item> findAll();

    Item update(ItemUpdateCommand command);

}
