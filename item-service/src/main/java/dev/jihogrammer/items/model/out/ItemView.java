package dev.jihogrammer.items.model.out;

import lombok.Data;

import java.util.Set;

@Data
public class ItemView {
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;
    private Boolean open;
    private Set<String> regions;
}
