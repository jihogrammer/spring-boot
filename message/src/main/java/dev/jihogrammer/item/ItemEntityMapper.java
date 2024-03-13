package dev.jihogrammer.item;

import dev.jihogrammer.item.model.in.ItemRegisterRequest;
import dev.jihogrammer.item.model.in.ItemUpdateRequest;
import dev.jihogrammer.item.model.out.ItemView;
import dev.jihogrammer.items.model.Item;
import dev.jihogrammer.items.model.ItemRegisterCommand;
import dev.jihogrammer.items.model.ItemUpdateCommand;

import java.util.Collection;

public class ItemEntityMapper {

    public static ItemView map(final Item item) {
        return ItemView.builder()
                .id(item.id().value())
                .name(item.name())
                .price(item.price())
                .quantity(item.quantity())
                .build();
    }

    public static Collection<ItemView> map(final Collection<Item> items) {
        return items.stream().map(ItemEntityMapper::map).toList();
    }

    public static ItemRegisterCommand map(final ItemRegisterRequest request) {
        return new ItemRegisterCommand(
                request.getName(),
                request.getPrice(),
                request.getQuantity(),
                null,
                null,
                null,
                null);
    }

    public static ItemUpdateCommand map(final ItemUpdateRequest request) {
        return new ItemUpdateCommand(
                request.getId(),
                request.getName(),
                request.getPrice(),
                request.getQuantity(),
                null,
                null,
                null,
                null);
    }

}
