package dev.jihogrammer.items.application.update;

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
public class ItemUpdatePayload {

    private String name;

    private Integer price;

    private Integer quantity;

    private Boolean open;

    private Set<String> regions;

    private ItemType itemType;

    private String deliveryCode;

    public static ItemUpdatePayload of(final Item item) {
        return new ItemUpdatePayload(
                item.name(),
                item.price(),
                item.quantity(),
                item.open(),
                item.regions(),
                item.itemType(),
                item.deliveryCode());
    }

    public ItemUpdateCommand toCommand(final Long id) {
        return new ItemUpdateCommand(
                id,
                this.name,
                this.price,
                this.quantity,
                this.open,
                this.regions,
                this.itemType,
                this.deliveryCode);
    }

}
