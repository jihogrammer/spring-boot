package dev.jihogrammer.item.application.service;

import dev.jihogrammer.item.adaptor.persistence.ItemPersistenceAdaptorFactory;
import dev.jihogrammer.item.application.port.in.ItemQuery;
import dev.jihogrammer.item.application.port.in.ItemUpdatePort;
import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import dev.jihogrammer.item.application.port.out.ItemSearchCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ItemQueryServiceTest {

    ItemQuery itemQuery;

    ItemUpdatePort itemUpdatePort;

    @BeforeEach
    void setUp() {
        var adaptorFactory = new ItemPersistenceAdaptorFactory();
        var serviceFactory = new ItemServiceFactory();

        var items = adaptorFactory.inMemoryItemAdaptor();

        this.itemQuery = serviceFactory.itemQuery(items);
        this.itemUpdatePort = serviceFactory.itemUpdatePort(items);
    }

    @Test
    void findById() {
        // given
        var command = new ItemSaveCommand(null, "item", 10, 20);
        var item = this.itemUpdatePort.register(command);

        // when
        var optionalItem = this.itemQuery.findById(item.id());

        // then
        assertThat(optionalItem).isPresent();
        assertThat(optionalItem.get()).isEqualTo(item);
    }

    @Test
    void searchByName() {
        // given
        this.itemUpdatePort.register(new ItemSaveCommand(null, "tomato", 10, 100));
        var apple = this.itemUpdatePort.register(new ItemSaveCommand(null, "apple", 20, 100));
        var pineapple = this.itemUpdatePort.register(new ItemSaveCommand(null, "pineapple", 30, 100));

        var command = new ItemSearchCommand("APPLE", null, null);

        // when
        var foundItems = this.itemQuery.search(command);

        // then
        assertThat(foundItems).contains(apple, pineapple);
    }

    @Test
    void searchByPrice() {
        // given
        var tomato = this.itemUpdatePort.register(new ItemSaveCommand(null, "tomato", 10, 100));
        var apple = this.itemUpdatePort.register(new ItemSaveCommand(null, "apple", 20, 100));
        this.itemUpdatePort.register(new ItemSaveCommand(null, "pineapple", 30, 100));

        var command = new ItemSearchCommand(null, tomato.price(), apple.price());

        // when
        var foundItems = this.itemQuery.search(command);

        // then
        assertThat(foundItems).contains(tomato, apple);
    }

}
