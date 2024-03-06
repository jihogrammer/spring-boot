package dev.jihogrammer.item.model.in;

import dev.jihogrammer.items.port.out.ItemRegisterCommand;
import lombok.Data;

@Data
public class ItemRegisterRequest {
    private String name;
    private Integer price;
    private Integer quantity;

    public ItemRegisterCommand mapToItemSaveCommand() {
        return ItemRegisterCommand.builder()
            .name(this.name)
            .price(this.price)
            .quantity(this.quantity)
            .build();
    }
}
