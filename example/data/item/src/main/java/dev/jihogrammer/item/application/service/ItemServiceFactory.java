package dev.jihogrammer.item.application.service;

import dev.jihogrammer.item.application.port.in.ItemQuery;
import dev.jihogrammer.item.application.port.in.ItemUpdatePort;
import dev.jihogrammer.item.application.port.out.Items;

public class ItemServiceFactory {

    public ItemQuery itemQuery(final Items items) {
        return new ItemQueryService(items);
    }

    public ItemUpdatePort itemUpdatePort(final Items items) {
        return new ItemUpdateService(items);
    }

}
