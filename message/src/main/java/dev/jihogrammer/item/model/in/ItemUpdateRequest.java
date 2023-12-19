package dev.jihogrammer.item.model.in;

import dev.jihogrammer.items.model.in.ItemUpdateCommand;
import lombok.Data;

@Data
public class ItemUpdateRequest {
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;

    public ItemUpdateCommand mapToItemUpdateCommand() {
        return new ItemUpdateCommand(this.id, this.name, this.price, this.quantity, null, null, null, null);
    }
}
