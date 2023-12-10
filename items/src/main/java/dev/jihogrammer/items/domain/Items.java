package dev.jihogrammer.items.domain;

import dev.jihogrammer.items.domain.model.Item;
import dev.jihogrammer.items.domain.model.ItemSaveCommand;
import dev.jihogrammer.items.domain.model.ItemUpdateCommand;
import dev.jihogrammer.items.vo.ItemId;

import java.util.Collection;

public interface Items {
    Item save(ItemSaveCommand command);
    Item findById(ItemId itemId);
    Collection<Item> findAll();
    Item update(ItemUpdateCommand command);
}
