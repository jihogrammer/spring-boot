package dev.jihogrammer.items.port.in;

import dev.jihogrammer.items.Item;

public interface ItemUpdateUsage {

    Item update(ItemUpdateCommand command);

}
