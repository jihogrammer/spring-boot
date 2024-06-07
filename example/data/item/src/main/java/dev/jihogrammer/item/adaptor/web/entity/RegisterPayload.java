package dev.jihogrammer.item.adaptor.web.entity;

import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import lombok.Data;

@Data
public class RegisterPayload {

    private String name;

    private Integer price;

    private Integer quantity;

    public ItemSaveCommand toCommand() {
        return new ItemSaveCommand(null, this.name, this.price, this.quantity);
    }

}
