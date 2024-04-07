package dev.jihogrammer.items.port.in;

import dev.jihogrammer.items.Item;
import dev.jihogrammer.items.model.ItemRegisterCommand;

public interface ItemRegisterUsage {

    Item register(ItemRegisterCommand command);

}
