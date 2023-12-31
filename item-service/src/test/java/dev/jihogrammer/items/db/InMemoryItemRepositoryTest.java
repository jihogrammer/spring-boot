package dev.jihogrammer.items.db;

import dev.jihogrammer.items.Items;
import dev.jihogrammer.items.Item;
import dev.jihogrammer.items.model.in.ItemSaveCommand;
import dev.jihogrammer.items.model.in.ItemUpdateCommand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryItemRepositoryTest {
    static final Items items = new InMemoryItemRepository(new HashMap<>(), new AtomicLong());

    @AfterEach
    void setUp() {
        ((InMemoryItemRepository) items).clearStore();
    }

    @Test
    void save() {
        // given
        ItemSaveCommand command = new ItemSaveCommand("item", 10_000, 10, null, null, null, null);
        // when
        Item savedItem = items.save(command);
        // then
        Item actual = items.findById(savedItem.id());
        assertThat(actual).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        // given
        Item savedItemA = items.save(new ItemSaveCommand("item-A", 1_000, 10, true, null, null, null));
        Item savedItemB = items.save(new ItemSaveCommand("item-B", 2_000, 20, false, null, null, null));
        // when
        Iterable<Item> actual = items.findAll();
        // then
        assertThat(actual).size().isEqualTo(2);
        assertThat(actual).contains(savedItemA, savedItemB);
    }

    @Test
    void update() {
        // given
        Item savedItem = items.save(new ItemSaveCommand("item", 1_000, 10, true, null, null, null));
        ItemUpdateCommand command = new ItemUpdateCommand(savedItem.id().value(), "updated", 2_000, 20, null, null, null, null);
        // when
        Item updatedItem = items.update(command);
        // then
        assertThat(items.findById(savedItem.id())).isEqualTo(updatedItem);
    }
}
