package dev.jihogrammer.items;

import dev.jihogrammer.items.model.vo.ItemId;
import dev.jihogrammer.items.model.in.ItemSaveCommand;
import dev.jihogrammer.items.model.in.ItemUpdateCommand;

import java.util.Collection;

public interface Items {
    Item save(ItemSaveCommand command);
    Item findById(ItemId itemId);
    Collection<Item> findAll();
    Item update(ItemUpdateCommand command);
}
