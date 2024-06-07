package dev.jihogrammer.item.application.port.in;

import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import dev.jihogrammer.item.domain.Item;

public interface ItemUpdatePort {

    Item register(ItemSaveCommand command);

    Item update(ItemSaveCommand command);

}
