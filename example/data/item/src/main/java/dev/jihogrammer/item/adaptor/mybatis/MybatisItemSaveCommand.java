package dev.jihogrammer.item.adaptor.mybatis;

import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;
import lombok.Data;

@Data
class MybatisItemSaveCommand {

    private Long itemId;

    private String name;

    private Integer price;

    private Integer quantity;

    public static MybatisItemSaveCommand of(final ItemSaveCommand command) {
        final var dto = new MybatisItemSaveCommand();

        dto.itemId = command.id();
        dto.name = command.name();
        dto.price = command.price();
        dto.quantity = command.quantity();

        return dto;
    }

    public Item toDomain() {
        return new Item(new ItemId(this.itemId), this.name, this.price, this.quantity);
    }

}
