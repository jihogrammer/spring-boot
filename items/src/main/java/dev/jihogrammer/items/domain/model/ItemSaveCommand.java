package dev.jihogrammer.items.domain.model;

import dev.jihogrammer.items.vo.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemSaveCommand {
    private String name;
    private Integer price;
    private Integer quantity;
    private Boolean open;
    private Set<String> regions;
    private ItemType itemType;
    private String deliveryCode;
}
