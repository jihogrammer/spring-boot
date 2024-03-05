package dev.jihogrammer.items.model.in;

import dev.jihogrammer.items.model.vo.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ItemUpdateCommand {
    private final Long id;
    private final String name;
    private final Integer price;
    private final Integer quantity;
    private Boolean open;
    private Set<String> regions;
    private ItemType itemType;
    private String deliveryCode;
}
