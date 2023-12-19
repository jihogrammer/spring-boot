package dev.jihogrammer.items.model.in;

import dev.jihogrammer.items.model.vo.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class ItemUpdateCommand {
    private final Long id;
    private String name;
    private Integer price;
    private Integer quantity;
    private Boolean open;
    private Set<String> regions;
    private ItemType itemType;
    private String deliveryCode;
}
