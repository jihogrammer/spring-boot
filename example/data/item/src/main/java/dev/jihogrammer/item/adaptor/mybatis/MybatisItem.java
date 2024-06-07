package dev.jihogrammer.item.adaptor.mybatis;

import dev.jihogrammer.item.domain.Item;
import dev.jihogrammer.item.domain.ItemId;
import lombok.Data;

@Data
class MybatisItem {

    private Long itemId;

    private String name;

    private Integer price;

    private Integer quantity;

    public Item toDomain() {
        return new Item(
                new ItemId(this.itemId),
                this.name,
                this.price,
                this.quantity);
    }

}
