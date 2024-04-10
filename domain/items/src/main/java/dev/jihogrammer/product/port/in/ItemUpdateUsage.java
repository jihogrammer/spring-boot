package dev.jihogrammer.product.port.in;

import dev.jihogrammer.product.Item;
import dev.jihogrammer.product.domain.model.ItemUpdateCommand;

public interface ItemUpdateUsage {

    Item update(ItemUpdateCommand command);

}
