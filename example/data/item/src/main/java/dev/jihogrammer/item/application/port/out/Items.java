package dev.jihogrammer.item.application.port.out;

import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;

import java.util.Collection;
import java.util.Optional;

public interface Items {

    String TABLE_NAME = "ITEMS";

    Item save(ItemSaveCommand command);

    Optional<Item> findById(ItemId id);

    Collection<Item> search(ItemSearchCommand command);

}
