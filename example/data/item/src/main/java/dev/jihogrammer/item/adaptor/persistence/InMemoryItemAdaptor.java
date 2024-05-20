package dev.jihogrammer.item.adaptor.persistence;

import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import dev.jihogrammer.item.application.port.out.ItemSearchCommand;
import dev.jihogrammer.item.application.port.out.Items;
import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
class InMemoryItemAdaptor implements Items {

    private final Map<ItemId, Item> items;

    @Override
    public Item save(final ItemSaveCommand command) {
        final Item item = new Item(
                new ItemId(command.id() == null ? UUID.randomUUID().toString() : command.id()),
                command.name(),
                command.price(),
                command.quantity());

        this.items.put(item.id(), item);

        return item;
    }

    @Override
    public Optional<Item> findById(final ItemId id) {
        return Optional.ofNullable(this.items.get(id));
    }

    @Override
    public Collection<Item> search(final ItemSearchCommand command) {
        return this.items.values().stream()
                .filter(item -> {
                    final var input = command.input();
                    return input == null || input.isBlank() || item.name().toLowerCase().contains(input);
                })
                .filter(item -> command.minPrice() <= item.price())
                .filter(item -> command.maxPrice() >= item.price())
                .toList();
    }

}
