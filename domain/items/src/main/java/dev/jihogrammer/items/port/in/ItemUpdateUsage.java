package dev.jihogrammer.items.port.in;

import dev.jihogrammer.items.model.Item;
import dev.jihogrammer.items.model.ItemUpdateCommand;

public interface ItemUpdateUsage {

    Item update(ItemUpdateCommand command);

}
