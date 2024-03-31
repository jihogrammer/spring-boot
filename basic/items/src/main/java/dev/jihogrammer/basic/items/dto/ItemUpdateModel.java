package dev.jihogrammer.basic.items.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import dev.jihogrammer.items.model.Item;
import dev.jihogrammer.items.model.ItemType;
import dev.jihogrammer.items.model.ItemUpdateCommand;
import lombok.Builder;

import java.util.Set;

@Builder(builderClassName = "Builder")
@JsonDeserialize(builder = ItemUpdateModel.Builder.class)
public record ItemUpdateModel(
        Long id,
        String name,
        Integer price,
        Integer quantity,
        Boolean open,
        Set<String> regions,
        ItemType itemType,
        String deliveryCode
) {

    public static ItemUpdateModel of(final Item item) {
        return new ItemUpdateModel(
                item.id().value(),
                item.name(),
                item.price(),
                item.quantity(),
                item.open(),
                item.regions(),
                item.itemType(),
                item.deliveryCode());
    }

    public ItemUpdateCommand toCommand() {
        return new ItemUpdateCommand(
                this.id,
                this.name,
                this.price,
                this.quantity,
                this.open,
                this.regions,
                this.itemType,
                this.deliveryCode);
    }

}
