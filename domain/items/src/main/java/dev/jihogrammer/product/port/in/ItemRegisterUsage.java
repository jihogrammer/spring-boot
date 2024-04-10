package dev.jihogrammer.product.port.in;

import dev.jihogrammer.product.Item;
import dev.jihogrammer.product.domain.model.ItemRegisterCommand;

public interface ItemRegisterUsage {

    Item register(ItemRegisterCommand command);

}
