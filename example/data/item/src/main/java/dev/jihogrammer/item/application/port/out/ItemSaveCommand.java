package dev.jihogrammer.item.application.port.out;

public record ItemSaveCommand(
        String id,
        String name,
        Integer price,
        Integer quantity
) {
}
