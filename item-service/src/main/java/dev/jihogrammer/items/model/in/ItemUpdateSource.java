package dev.jihogrammer.items.model.in;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ItemUpdateSource {
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;
    private Boolean open;
    private Set<String> regions;
}
