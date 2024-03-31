package dev.jihogrammer.basic.items.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.jihogrammer.items.model.ItemRegisterCommand;
import dev.jihogrammer.items.model.ItemType;
import lombok.Builder;

import java.util.Set;

@Builder(builderClassName = "Builder")
@JsonDeserialize(builder = ItemRegisterModel.Builder.class)
public record ItemRegisterModel(
        String name,
        Integer price,
        Integer quantity,
        Boolean open,
        Set<String> regions,
        ItemType itemType,
        String deliveryCode
) {

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
