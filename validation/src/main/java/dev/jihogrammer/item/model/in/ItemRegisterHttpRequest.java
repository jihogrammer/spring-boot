package dev.jihogrammer.item.model.in;

import dev.jihogrammer.items.model.in.ItemSaveCommand;
import lombok.Data;

@Data
public class ItemRegisterHttpRequest {
    private String name;
    private Integer price;
    private Integer quantity;

    public ItemSaveCommand mapToCommand() {
        return new ItemSaveCommand(this.name, this.price, this.quantity);
    }
}
