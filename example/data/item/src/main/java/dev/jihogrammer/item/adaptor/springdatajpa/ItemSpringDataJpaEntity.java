package dev.jihogrammer.item.adaptor.springdatajpa;

import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ITEMS")
public class ItemSpringDataJpaEntity {

    @Id
    @Column(name = "ITEM_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private Integer quantity;

    static ItemSpringDataJpaEntity of(final ItemSaveCommand command) {
        final var entity = new ItemSpringDataJpaEntity();

        entity.id = command.id();
        entity.name = command.name();
        entity.price = command.price();
        entity.quantity = command.quantity();

        return entity;
    }

    Item toEntity() {
        return new Item(new ItemId(this.id), this.name, this.price, this.quantity);
    }

}
