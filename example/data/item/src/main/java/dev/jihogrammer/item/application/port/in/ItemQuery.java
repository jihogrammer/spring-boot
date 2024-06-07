package dev.jihogrammer.item.application.port.in;

import dev.jihogrammer.item.application.port.out.ItemSearchCommand;
import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;

import java.util.Collection;
import java.util.Optional;

public interface ItemQuery {

    Optional<Item> findById(ItemId id);

    Collection<Item> search(ItemSearchCommand command);

}
