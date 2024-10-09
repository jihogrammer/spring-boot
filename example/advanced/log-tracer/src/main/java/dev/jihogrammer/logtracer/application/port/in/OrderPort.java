package dev.jihogrammer.logtracer.application.port.in;

import dev.jihogrammer.logtracer.domain.ItemId;

public interface OrderPort {

    void orderItem(ItemId itemId);

}
