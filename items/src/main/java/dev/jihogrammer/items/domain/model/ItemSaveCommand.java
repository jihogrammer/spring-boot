package dev.jihogrammer.items.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemSaveCommand {
    private String name;
    private Integer price;
    private Integer quantity;
    private Boolean open;
}
