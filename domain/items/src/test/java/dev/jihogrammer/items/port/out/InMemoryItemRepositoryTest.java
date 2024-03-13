package dev.jihogrammer.items.port.out;

import dev.jihogrammer.items.model.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryItemRepositoryTest {

    InMemoryItemRepository items = new InMemoryItemRepository();

    @AfterEach
    void setUp() {
        this.items.clear();
    }

    @Test
    void save() {
        // given
        var item = new Item(this.items.nextId(), "item", 10_000, 10, null, null, null, null);

        // when
        var savedItem = items.save(item);

        // then
        var optionalItem = items.findById(savedItem.id());
        assertThat(optionalItem).isPresent();
        assertThat(optionalItem.get()).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        // given
        var savedItemA = items.save(new Item(this.items.nextId(), "item-A", 1_000, 10, true, null, null, null));
        var savedItemB = items.save(new Item(this.items.nextId(), "item-B", 2_000, 20, false, null, null, null));

        // when
        Iterable<Item> actual = items.findAll();

        // then
        assertThat(actual).size().isEqualTo(2);
        assertThat(actual).contains(savedItemA, savedItemB);
    }

}
