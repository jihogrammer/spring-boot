package dev.jihogrammer.spring.basic.entity;

import dev.jihogrammer.items.model.Item;
import dev.jihogrammer.items.model.ItemType;
import dev.jihogrammer.items.model.ItemUpdateCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemUpdateSource {

    private Long id;

    private String name;

    private Integer price;

    private Integer quantity;

    private Boolean open;

    private Set<String> regions;

    private ItemType itemType;

    private String deliveryCode;

    public static ItemUpdateSource of(final Item item) {
        return new ItemUpdateSource(
                item.id().value(),
                item.name(),
                item.price(),
                item.quantity(),
                item.open(),
                item.regions(),
                item.itemType(),
                item.deliveryCode());
    }

    public static ItemUpdateCommand of(final ItemUpdateSource itemUpdateSource) {
        return new ItemUpdateCommand(
                itemUpdateSource.getId(),
                itemUpdateSource.getName(),
                itemUpdateSource.getPrice(),
                itemUpdateSource.getQuantity(),
                itemUpdateSource.getOpen(),
                itemUpdateSource.getRegions(),
                itemUpdateSource.getItemType(),
                itemUpdateSource.getDeliveryCode());
    }

}
