package dev.jihogrammer.item.adaptor.web.entity;

import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import dev.jihogrammer.item.domain.Item;
import lombok.Data;

@Data
public class UpdatePayload {

    private Long id;

    private String name;

    private Integer price;

    private Integer quantity;

    public static UpdatePayload of(final Item item) {
        UpdatePayload payload = new UpdatePayload();

        payload.id = item.id().value();
        payload.name = item.name();
        payload.price = item.price();
        payload.quantity = item.quantity();

        return payload;
    }

    public ItemSaveCommand toCommand() {
        return new ItemSaveCommand(this.id, this.name, this.price, this.quantity);
    }

}
