package dev.jihogrammer.items.model.in;

import lombok.Data;

@Data
public class ItemUpdateSource {
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;
    private Boolean open;
}
