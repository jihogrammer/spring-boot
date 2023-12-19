package dev.jihogrammer.item.model.out;

import dev.jihogrammer.items.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemView {
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;

    public static ItemView of(final Item item) {
        return new ItemView(
            item.id().value(),
            item.name().value(),
            item.price().value(),
            item.quantity().value());
    }
}
