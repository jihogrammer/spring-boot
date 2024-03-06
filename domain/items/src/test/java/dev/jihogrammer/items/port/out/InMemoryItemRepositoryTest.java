package dev.jihogrammer.items.port.out;

import dev.jihogrammer.items.Item;
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
        var command = new ItemRegisterCommand("item", 10_000, 10, null, null, null, null);

        // when
        var savedItem = items.save(command);

        // then
        var actual = items.findById(savedItem.id());
        assertThat(actual).isEqualTo(savedItem);
    }

    @Test
    void findAll() {
        // given
        var savedItemA = items.save(new ItemRegisterCommand("item-A", 1_000, 10, true, null, null, null));
        var savedItemB = items.save(new ItemRegisterCommand("item-B", 2_000, 20, false, null, null, null));

        // when
        Iterable<Item> actual = items.findAll();

        // then
        assertThat(actual).size().isEqualTo(2);
        assertThat(actual).contains(savedItemA, savedItemB);
    }

    @Test
    void update() {
        // given
        var savedItem = this.items.save(new ItemRegisterCommand("item", 1_000, 10, true, null, null, null));
        var command = new ItemUpdateCommand(savedItem.id().value(), "updated", 2_000, 20, null, null, null, null);

        // when
        var updatedItem = this.items.update(command);

        // then
        assertThat(this.items.findById(savedItem.id())).isEqualTo(updatedItem);
    }

}
