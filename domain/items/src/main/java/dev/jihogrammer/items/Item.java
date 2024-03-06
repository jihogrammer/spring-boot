package dev.jihogrammer.items;

import dev.jihogrammer.items.port.out.ItemUpdateCommand;
import dev.jihogrammer.items.model.vo.*;
import dev.jihogrammer.items.port.out.ItemRegisterCommand;

import java.util.Set;

import static java.util.Objects.isNull;

public record Item(
    ItemId id,
    String name,
    Integer price,
    Integer quantity,
    Boolean open,
    Set<String> regions,
    ItemType itemType,
    String deliveryCode
) {

    public Item {
        if (isNull(id)) {
            throw new IllegalArgumentException("item id is null");
        }
        if (isNull(name) || name.isBlank()) {
            throw new IllegalArgumentException("item name is blank");
        }
    }

    public static Item of(final ItemId itemId, final ItemRegisterCommand command) {
        return new Item(
            itemId,
            command.name(),
            command.price(),
            command.quantity(),
            command.isOpen(),
            command.regions(),
            command.itemType(),
            command.deliveryCode());
    }

    public static Item of(final ItemUpdateCommand command) {
        return new Item(
            new ItemId(command.id()),
            command.name(),
            command.price(),
            command.quantity(),
            command.open(),
            command.regions(),
            command.itemType(),
            command.deliveryCode());
    }

}
