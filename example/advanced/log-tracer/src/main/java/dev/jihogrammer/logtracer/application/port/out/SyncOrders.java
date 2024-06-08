package dev.jihogrammer.logtracer.application.port.out;

import dev.jihogrammer.logtracer.domain.ItemId;
import dev.jihogrammer.logtracer.domain.TraceId;

public interface SyncOrders extends Orders {

    void save(TraceId prevId, ItemId itemId);

}
