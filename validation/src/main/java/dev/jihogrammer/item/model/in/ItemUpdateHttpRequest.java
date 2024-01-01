package dev.jihogrammer.item.model.in;

import dev.jihogrammer.item.model.out.ItemView;
import dev.jihogrammer.items.model.in.ItemSaveCommand;
import dev.jihogrammer.items.model.in.ItemUpdateCommand;
import lombok.Data;

@Data
public class ItemUpdateHttpRequest {
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;

    public ItemUpdateCommand mapToCommand() {
        return new ItemUpdateCommand(this.id, this.name, this.price, this.quantity);
    }

    public static ItemUpdateHttpRequest of(final ItemView itemView) {
        ItemUpdateHttpRequest request = new ItemUpdateHttpRequest();
        request.setId(itemView.id());
        request.setName(itemView.name());
        request.setPrice(itemView.price());
        request.setQuantity(itemView.quantity());
        return request;
    }
}
