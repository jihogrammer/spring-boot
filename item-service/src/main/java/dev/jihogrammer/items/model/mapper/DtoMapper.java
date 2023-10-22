package dev.jihogrammer.items.model.mapper;

import dev.jihogrammer.items.domain.Item;
import dev.jihogrammer.items.domain.ItemId;
import dev.jihogrammer.items.domain.ItemUpdateSource;
import dev.jihogrammer.items.model.request.ItemCreateDto;
import dev.jihogrammer.items.model.request.ItemUpdateDto;

public class DtoMapper {
    public static ItemUpdateSource map(final ItemUpdateDto dto) {
        return new ItemUpdateSource(new ItemId(dto.getId()), dto.getName(), dto.getPrice(), dto.getQuantity());
    }

    public static Item map(final ItemCreateDto dto) {
        return new Item(dto.getName(), dto.getPrice(), dto.getQuantity());
    }
}
