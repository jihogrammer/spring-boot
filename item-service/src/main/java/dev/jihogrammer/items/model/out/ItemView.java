package dev.jihogrammer.items.model.out;

import dev.jihogrammer.items.Item;
import dev.jihogrammer.items.model.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collection;
import java.util.Set;

@Data
@AllArgsConstructor
public class ItemView {

    private Long id;

    private String name;

    private Integer price;

    private Integer quantity;

    private Boolean open;

    private Set<String> regions;

    private ItemType itemType;

    private String deliveryCode;

    public static ItemView of(final Item item) {
        return new ItemView(
                item.id().value(),
                item.name(),
                item.price(),
                item.quantity(),
                item.open(),
                item.regions(),
                item.itemType(),
                item.deliveryCode());
    }

    public static Collection<ItemView> of(final Collection<Item> items) {
        return items.stream().map(ItemView::of).toList();
    }

}
