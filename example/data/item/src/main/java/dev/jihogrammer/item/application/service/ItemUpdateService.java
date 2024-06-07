package dev.jihogrammer.item.application.service;

import dev.jihogrammer.item.application.port.in.ItemUpdatePort;
import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import dev.jihogrammer.item.application.port.out.Items;
import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.exception.ItemException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class ItemUpdateService implements ItemUpdatePort {

    private final Items items;

    @Override
    public Item register(final ItemSaveCommand command) {
        if (command == null) {
            throw new ItemException("Could not save item using null command.");
        }
        return this.items.save(command);
    }

    @Override
    public Item update(final ItemSaveCommand command) {
        if (command == null) {
            throw new ItemException("Could not update item using null command.");
        }
        if (command.id() == null) {
            throw new ItemException("Could not update item using null id.");
        }
        return this.items.save(command);
    }

}
