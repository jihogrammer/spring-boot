package dev.jihogrammer.item.adaptor.persistence;

import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import dev.jihogrammer.item.application.port.out.ItemSearchCommand;
import dev.jihogrammer.item.application.port.out.Items;
import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;
import lombok.RequiredArgsConstructor;
import org.springframework.util.IdGenerator;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor
class InMemoryItemAdaptor implements Items {

    private final Map<ItemId, Item> items;

    @Override
    public Item save(final ItemSaveCommand command) {
        final var item = new Item(
                new ItemId(command.id() == null ? IdGenerator.next() : command.id()),
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
        final var input = command.input();
        final var minPrice = command.minPrice();
        final var maxPrice = command.maxPrice();

        return this.items.values().stream()
                .filter(item -> input == null || input.isBlank() || item.name().toLowerCase().contains(input))
                .filter(item -> minPrice <= item.price() && item.price() <= maxPrice)
                .toList();
    }

    private static class IdGenerator {

        private static final AtomicLong SEQUENCE = new AtomicLong(1);

        private static long next() {
            return SEQUENCE.getAndIncrement();
        }

    }

}
