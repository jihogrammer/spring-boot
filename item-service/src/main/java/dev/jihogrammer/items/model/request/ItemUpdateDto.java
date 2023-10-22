package dev.jihogrammer.items.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemUpdateDto {
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;
}
