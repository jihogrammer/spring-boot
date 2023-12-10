package dev.jihogrammer.items.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ItemUpdateCommand {
    private final Long id;
    private String name;
    private Integer price;
    private Integer quantity;
}
