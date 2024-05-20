package dev.jihogrammer.item.application.service;

import dev.jihogrammer.item.adaptor.persistence.ItemPersistenceAdaptorFactory;
import dev.jihogrammer.item.application.port.in.ItemUpdatePort;
import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ItemUpdateServiceTest {

    ItemUpdatePort itemUpdatePort;

    @BeforeEach
    void setUp() {
        var adaptorFactory = new ItemPersistenceAdaptorFactory();
        var serviceFactory = new ItemServiceFactory();

        var items = adaptorFactory.inMemoryItemAdaptor();

        this.itemUpdatePort = serviceFactory.itemUpdatePort(items);
    }

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
