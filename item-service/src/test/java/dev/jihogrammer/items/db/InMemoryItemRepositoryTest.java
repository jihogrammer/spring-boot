package dev.jihogrammer.items.db;

import dev.jihogrammer.items.domain.Item;
import dev.jihogrammer.items.domain.ItemUpdateSource;
import dev.jihogrammer.items.domain.Items;
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
        Item item = new Item("item", 10000, 10);
        // when
        Item savedItem = items.save(item);
        // then
        Item actual = items.findById(savedItem.getId());
        assertThat(actual).isEqualTo(savedItem);
        assertThat(actual.getName()).isEqualTo(item.getName());
        assertThat(actual.getPrice()).isEqualTo(item.getPrice());
        assertThat(actual.getQuantity()).isEqualTo(item.getQuantity());
    }

    @Test
    void findAll() {
        // given
        Item itemA = new Item("item-A", 1000, 10);
        Item itemB = new Item("item-B", 2000, 20);
        Item savedItemA = items.save(itemA);
        Item savedItemB = items.save(itemB);
        // when
        Iterable<Item> actual = items.findAll();
        // then
        assertThat(actual).size().isEqualTo(2);
        assertThat(actual).contains(savedItemA, savedItemB);
    }

    @Test
    void update() {
        // given
        Item item = new Item("item", 1000, 10);
        Item savedItem = items.save(item);
        ItemUpdateSource source = new ItemUpdateSource(savedItem.getId(), "updated", 2000, 20);
        // when
        items.update(source);
        // then
        Item actual = items.findById(savedItem.getId());
        assertThat(actual.getName()).isEqualTo(source.name());
        assertThat(actual.getPrice()).isEqualTo(source.price());
        assertThat(actual.getQuantity()).isEqualTo(source.quantity());
    }
}