package dev.jihogrammer.items.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCreateDto {
    private String name;
    private Integer price;
    private Integer quantity;
}
