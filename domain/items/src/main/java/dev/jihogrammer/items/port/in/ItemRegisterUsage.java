package dev.jihogrammer.items.port.in;

import dev.jihogrammer.items.Item;

public interface ItemRegisterUsage {

    Item register(ItemRegisterCommand command);

}
