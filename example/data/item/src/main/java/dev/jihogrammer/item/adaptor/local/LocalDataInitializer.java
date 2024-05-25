package dev.jihogrammer.item.adaptor.local;

import dev.jihogrammer.item.application.port.in.ItemUpdatePort;
import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@RequiredArgsConstructor
public class LocalDataInitializer {

    private final ItemUpdatePort itemUpdatePort;

    @EventListener(ApplicationReadyEvent.class)
    void init() {
        this.itemUpdatePort.register(new ItemSaveCommand(null, "test-item-1", 1000, 10));
        this.itemUpdatePort.register(new ItemSaveCommand(null, "test-item-2", 2000, 20));
        this.itemUpdatePort.register(new ItemSaveCommand(null, "test-item-3", 3000, 30));
    }

}
