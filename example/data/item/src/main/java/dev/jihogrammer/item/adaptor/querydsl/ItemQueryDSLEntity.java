package dev.jihogrammer.item.adaptor.querydsl;

import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ITEMS")
class ItemQueryDSLEntity {

    @Id
    @Column(name = "ITEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private Integer quantity;

    static ItemQueryDSLEntity of(final ItemSaveCommand command) {
        final var entity = new ItemQueryDSLEntity();

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
