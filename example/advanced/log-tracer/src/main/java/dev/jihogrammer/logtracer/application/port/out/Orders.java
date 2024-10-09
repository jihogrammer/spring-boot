package dev.jihogrammer.logtracer.application.port.out;

import dev.jihogrammer.logtracer.domain.ItemId;

public interface Orders {

    void save(ItemId itemId);

}
