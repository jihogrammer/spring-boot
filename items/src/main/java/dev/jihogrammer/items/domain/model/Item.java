package dev.jihogrammer.items.domain.model;

import dev.jihogrammer.items.vo.*;

import java.util.Set;

public record Item(
    ItemId id,
    ItemName name,
    ItemPrice price,
    ItemQuantity quantity,
    Boolean open,
    Set<String> regions,
    ItemType itemType,
    DeliveryCode deliveryCode
) {
    public ItemName name() {
        if (this.name == null) {
            return new ItemName(null);
        }
        return this.name;
    }

    public ItemPrice price() {
        if (this.price == null) {
            return new ItemPrice(null);
        }
        return this.price;
    }

    public ItemQuantity quantity() {
        if (this.quantity == null) {
            return new ItemQuantity(null);
        }
        return this.quantity;
    }
}
