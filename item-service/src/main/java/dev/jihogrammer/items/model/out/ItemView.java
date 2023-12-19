package dev.jihogrammer.items.model.out;

import dev.jihogrammer.items.model.vo.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ItemView {
    private Long id;
    private String name;
    private Integer price;
    private Integer quantity;
    private Boolean open;
    private Set<String> regions;
    private ItemType itemType;
    private String deliveryCode;
}
