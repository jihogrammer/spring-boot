package dev.jihogrammer.items.application.register;

import dev.jihogrammer.items.model.ItemRegisterCommand;
import dev.jihogrammer.items.model.ItemType;
import lombok.Data;

import java.util.Set;

@Data
public class ItemRegisterPayload {

    private String name;

    private Integer price;

    private Integer quantity;

    private Boolean open;

    private Set<String> regions;

    private ItemType itemType;

    private String deliveryCode;

    public ItemRegisterCommand toCommand() {
        return new ItemRegisterCommand(
                this.name,
                this.price,
                this.quantity,
                this.open,
                this.regions,
                this.itemType,
                this.deliveryCode);
    }

}
