package dev.jihogrammer.item.application.port.out;

public record ItemSaveCommand(
        Long id,
        String name,
        Integer price,
        Integer quantity
) {
}
