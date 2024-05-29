package dev.jihogrammer.item.adaptor.mybatis;

import dev.jihogrammer.item.application.port.out.ItemSaveCommand;
import lombok.Data;

@Data
public class ItemSaveCommandDto {

    private Long itemId;

    private String name;

    private Integer price;

    private Integer quantity;

    public static ItemSaveCommandDto of(final ItemSaveCommand command) {
        final var dto = new ItemSaveCommandDto();

        dto.itemId = command.id();
        dto.name = command.name();
        dto.price = command.price();
        dto.quantity = command.quantity();

        return dto;
    }

}
