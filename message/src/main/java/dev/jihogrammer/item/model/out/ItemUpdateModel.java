package dev.jihogrammer.item.model.out;

import dev.jihogrammer.items.domain.model.Item;
import dev.jihogrammer.items.domain.model.ItemUpdateCommand;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemUpdateModel {
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;

    public static ItemUpdateModel of(final Item item) {
        return new ItemUpdateModel(item.id().value(), item.name().value(), item.price().value(), item.quantity().value());
    }
}
