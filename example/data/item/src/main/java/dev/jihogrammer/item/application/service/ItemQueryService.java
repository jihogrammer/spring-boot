package dev.jihogrammer.item.application.service;

import dev.jihogrammer.item.application.port.in.ItemQuery;
import dev.jihogrammer.item.application.port.out.ItemSearchCommand;
import dev.jihogrammer.item.application.port.out.Items;
import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;
import dev.jihogrammer.item.domain.exception.ItemException;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;

@RequiredArgsConstructor
class ItemQueryService implements ItemQuery {

    private final Items items;

    @Override
    public Optional<Item> findById(final ItemId id) {
        if (id == null) {
            throw new ItemException("Could not find a item using null id.");
        }
        return this.items.findById(id);
    }

    @Override
    public Collection<Item> search(final ItemSearchCommand command) {
        return this.items.search(command == null ? ItemSearchCommand.DEFAULT_COMMAND : command);
    }

}
