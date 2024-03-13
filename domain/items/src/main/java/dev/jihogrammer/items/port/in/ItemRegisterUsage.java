package dev.jihogrammer.items.port.in;

import dev.jihogrammer.items.model.Item;
import dev.jihogrammer.items.model.ItemRegisterCommand;

public interface ItemRegisterUsage {

    Item register(ItemRegisterCommand command);

}
