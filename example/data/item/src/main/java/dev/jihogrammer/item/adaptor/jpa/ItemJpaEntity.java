package dev.jihogrammer.item.adaptor.jpa;

import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import dev.jihogrammer.item.application.port.out.Items;
import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = Items.TABLE_NAME)
class ItemJpaEntity {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 16)
    private String name;

    private Integer price;

    private Integer quantity;

    static ItemJpaEntity of(final ItemSaveCommand command) {
        ItemJpaEntity entity = new ItemJpaEntity();

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
