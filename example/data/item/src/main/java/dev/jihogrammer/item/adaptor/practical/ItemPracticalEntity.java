package dev.jihogrammer.item.adaptor.practical;

import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ITEMS")
class ItemPracticalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;

    private Integer price;

    private Integer quantity;

    static ItemPracticalEntity of(final ItemSaveCommand command) {
        final var entity = new ItemPracticalEntity();

        entity.id = command.id();
        entity.name = command.name();
        entity.price = command.price();
        entity.quantity = command.quantity();

        return entity;
    }

    Item toDomainEntity() {
        return new Item(new ItemId(this.id), this.name, this.price, this.quantity);
    }

}
