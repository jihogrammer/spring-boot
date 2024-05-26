package dev.jihogrammer.item.application.service;

import dev.jihogrammer.item.IntegrationTest;
import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ItemUpdateServiceTest extends IntegrationTest {

    @Test
    void register() {
        // given
        var command = new ItemSaveCommand(null, "item", 10, 20);

        // when
        var item = this.itemUpdatePort.register(command);

        // then
        assertThat(item.id()).isNotNull();
        assertThat(item.name()).isEqualTo(command.name());
        assertThat(item.price()).isEqualTo(command.price());
        assertThat(item.quantity()).isEqualTo(command.quantity());
    }

    @Test
    void update() {
        // given
        var savedItem = this.itemUpdatePort.register(new ItemSaveCommand(null, "item", 10, 20));
        var command = new ItemSaveCommand(savedItem.id().value(), "updatedName", 20, 30);

        // when
        var item = this.itemUpdatePort.update(command);

        // then
        assertThat(item.id().value()).isEqualTo(command.id());
        assertThat(item.name()).isEqualTo(command.name());
        assertThat(item.price()).isEqualTo(command.price());
        assertThat(item.quantity()).isEqualTo(command.quantity());
    }

}
