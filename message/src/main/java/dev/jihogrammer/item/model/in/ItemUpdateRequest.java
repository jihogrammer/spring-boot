package dev.jihogrammer.item.model.in;

import lombok.Data;

@Data
public class ItemUpdateRequest {

    private Long id;

    private String name;

    private Integer price;

    private Integer quantity;

}
