package dev.jihogrammer.item.model.in;

import dev.jihogrammer.items.model.in.ItemSaveCommand;
import lombok.Data;

@Data
public class ItemRegisterRequest {
    private String name;
    private Integer price;
    private Integer quantity;

    public ItemSaveCommand mapToItemSaveCommand() {
        return new ItemSaveCommand(this.name, this.price, this.quantity, null, null, null, null);
    }
}
