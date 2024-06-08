package dev.jihogrammer.logtracer.application.port.in;

import dev.jihogrammer.logtracer.domain.ItemId;
import dev.jihogrammer.logtracer.domain.TraceId;

public interface SyncOrderPort extends OrderPort {

    void orderItem(TraceId prevId, ItemId itemId);

}
