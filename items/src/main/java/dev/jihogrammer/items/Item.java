package dev.jihogrammer.items;

import dev.jihogrammer.items.model.vo.ItemId;
import dev.jihogrammer.items.model.vo.ItemName;
import dev.jihogrammer.items.model.vo.ItemPrice;
import dev.jihogrammer.items.model.vo.ItemQuantity;
import dev.jihogrammer.items.model.vo.ItemType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Set;

import static java.util.Objects.requireNonNull;

@Getter
@Accessors(fluent = true)
@EqualsAndHashCode
public class Item {
    private final ItemId id;
    private final ItemName name;
    private final ItemPrice price;
    private final ItemQuantity quantity;
    private Boolean open;
    private Set<String> regions;
    private ItemType itemType;
    private String deliveryCode;

    public Item(final ItemId id, final ItemName name, final ItemPrice price, final ItemQuantity quantity) {
        this.id = requireNonNull(id);
        this.name = requireNonNull(name);
        this.price = requireNonNull(price);
        this.quantity = requireNonNull(quantity);
    }

    public Item(
        final ItemId id,
        final ItemName name,
        final ItemPrice price,
        final ItemQuantity quantity,
        final Boolean open,
        final Set<String> regions,
        final ItemType itemType,
        final String deliveryCode
    ) {
        this.id = requireNonNull(id);
        this.name = requireNonNull(name);
        this.price = requireNonNull(price);
        this.quantity = requireNonNull(quantity);
        this.open = open;
        this.regions = regions;
        this.itemType = itemType;
        this.deliveryCode = deliveryCode;
    }
}
